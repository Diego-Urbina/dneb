package es.ucm.si.dneb.service.busquedaDobles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Jama.Matrix;

import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientosException;
import es.ucm.si.dneb.service.image.centroid.CalculateBookCentroid;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.RectStar;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;
import es.ucm.si.dneb.service.image.util.Point;

@Service("serviceBusquedaDobles")
public class ServiceBusquedaDoblesImpl implements ServiceBusquedaDobles{
	
	@PersistenceContext
	private EntityManager manager;
	
	
	private static final Log LOG = LogFactory
	.getLog(ServiceBusquedaDoblesImpl.class);
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarProcesamiento(List<ProcImagen> procImgs) {
		// TODO Auto-generated method stub
		
		
		/* Algoritmo de busqueda de estrellas
		 * 
		 * 1 - Buscar todas las estrellas en las dos imagenes con el algoritmo de busqueda de estrellas
		 * 2 - Coger de ambas imagenes el minimo numero de estrellas encontradas. La imagen con menos estrellas
		 *     será la de los centroides a analizar y la otra la de los emparejamientos
		 * 3 - Obtener los centroides de las estrellas encontradas
		 * 4 - Comparar cada centroide de la primera imagen con los de la segunda e ir emparejandolos
		 * 4.1 - Se coge el centroide mas cercano al que se quiere emparejar y se toma como su pareja
		 * 4.2 - Si no se encuentra centroide dentro de un radio dado se desecha el centroide
		 * 4.3 - Si el porcentaje de centroides emparejados es menor a un numero dado se desechan las imagenes
		 * 4.4 - Si es mayor o igual se pasa al tratamiento de ambas imagenes
		 * 5 - Por ultimo, se calcula la matriz de transformacion para ajustar las dos imagenes con respecto a los centroides elegidos
		 */
		
		// Obtener nombre de ficheros y parametros configurables
		String filename1 = procImgs.get(0).getImagen().getRutaFichero(), filename2 = procImgs.get(1).getImagen().getRutaFichero();
		List<ParamProcTarea> paramProcTareas = procImgs.get(0).getTareaProcesamiento().getParametros();
		double brillo = 0, umbral = 0;
		for (int i = 0; i < paramProcTareas.size(); i++) {
			if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
				brillo = paramProcTareas.get(i).getValorNum();
			if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
				umbral = paramProcTareas.get(i).getValorNum();
		}
		
		try {
			
			// Crear imagenes y buscar estrellas
			Fits imagenFITS1 = new Fits(new File(filename1));			
			BasicHDU imageHDU1 = imagenFITS1.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU1, filename1);
			StarFinder sf1 = new StarFinder();
			sf1.buscarEstrellas(l1, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf1.getNumberOfStars());
			
			Fits imagenFITS2 = new Fits(new File(filename2));			
			BasicHDU imageHDU2 = imagenFITS2.getHDU(0);
			LectorImageHDU l2 = new LectorImageHDU(imageHDU2, filename2);
			StarFinder sf2 = new StarFinder();
			sf2.buscarEstrellas(l2, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf2.getNumberOfStars());

			ArrayList<RectStar> recuadros1, recuadros2;
			int nRecuadros = Math.min(sf1.getNumberOfStars(), sf2.getNumberOfStars());
			LectorImageHDU aux;
			if (nRecuadros == sf1.getNumberOfStars()) {
				recuadros1 = sf1.getRecuadros();
				recuadros2 = sf2.getRecuadros();
			} else {
				aux = l1;
				l1 = l2;
				l2 = aux;
				recuadros1 = sf2.getRecuadros();
				recuadros2 = sf1.getRecuadros();
			}
			
			// Calcular los centroides de los recuadros de ambas imagenes
			ArrayList<Point> centroides1 = new ArrayList<Point>(), centroides2 = new ArrayList<Point>();
			CalculateBookCentroid cc = new CalculateBookCentroid();
			Point cent1, cent2;
			for (int i = 0; i < nRecuadros; i++) {
				cent1 = cc.giveMeTheCentroid(l1.getPorcionImagen(recuadros1.get(i).getxLeft(), recuadros1.get(i).getyTop(), 
						recuadros1.get(i).getWidth(), recuadros1.get(i).getHeight()));
				cent2 = cc.giveMeTheCentroid(l2.getPorcionImagen(recuadros2.get(i).getxLeft(), recuadros2.get(i).getyTop(), 
						recuadros2.get(i).getWidth(), recuadros2.get(i).getHeight()));
				
				centroides1.add(cent1);
				centroides2.add(cent2);
			}
			
			// Hacer coincidir los nRecuadros centroides
			double scaleW, scaleH;
			scaleW = l2.getWidth()/l1.getWidth();
			scaleH = l2.getHeight()/l1.getHeight();
			double porcentaje = 0;
			Point centroide, elegido;
			ArrayList<Point> elegidos = new ArrayList<Point>();
			ArrayList<Point> centroides = new ArrayList<Point>();
			for (int i = 0; i < nRecuadros; i++) {
				// Escalar el centroide
				centroide = centroides1.get(i);
				centroide.setX(centroide.getX() * scaleW);
				centroide.setY(centroide.getY() * scaleH);
				elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2);
				
				if (elegido != null) { // se ha encontrado coincidente
					
					// Comprobar si el centroide ya esta en el array
					if (elegidos.contains(elegido)) {
						int pos = elegidos.indexOf(elegido);
						
						// Si la distancia del nuevo centroide es menor que la del antiguo se descarta el antiguo
						if (centroide.getDistancia(elegido) <
								centroides.get(pos).getDistancia(elegido)) {
							centroides.remove(pos);
							elegidos.remove(pos);
						}
					}
					
					centroides.add(centroide);
					elegidos.add(elegido);
					porcentaje++;
				}
			}
			
			porcentaje = (porcentaje/nRecuadros) * 100;
			System.out.println("Recuadros elegidos: " + nRecuadros);
			System.out.println("El porcentaje de centroides elegidos es: " + porcentaje);
			
			// Construir las matrices de ambas listas y encontrar la matriz de transformacion
			// Y a la vez se calcula el error inicial
			double errorInicial = 0;
			if (porcentaje >= 50) {
				double[][] m1 = new double[centroides.size()][3], m2 = new double[elegidos.size()][3];
				for (int i = 0; i < centroides.size(); i++) {
					centroide = centroides.get(i);
					m1[i][0] = centroide.getX();
					m1[i][1] = centroide.getY();
					m1[i][2] = 1;
					
					centroide = elegidos.get(i);
					m2[i][0] = centroide.getX();
					m2[i][1] = centroide.getY();
					m2[i][2] = 1;
					
					errorInicial += Math.sqrt(Math.pow((m1[i][0] - m2[i][0]),2) +
									Math.pow((m1[i][1] - m2[i][1]),2));
				}
				JOptionPane.showMessageDialog(null, errorInicial);
				
				Matrix P = new Matrix(m1);
				Matrix Q = new Matrix(m2);
				Matrix X = P.solve(Q);
				Matrix E = P.times(X).minus(Q);
			    double errorFinal = 0, prov = 0;
			    for (int i = 0; i < E.getRowDimension(); i++) {
			    	for (int j = 0; j < E.getColumnDimension()-1; j++) {
			    		prov += Math.pow(E.get(i, j), 2);
			    	}
			    	errorFinal += Math.sqrt(prov);
			    }
			    JOptionPane.showMessageDialog(null, errorFinal);
			}
			
			// Comparar errores
			
			/*procImgs.get(0).setFinalizada(true);
			procImgs.get(1).setFinalizada(true);
			
			manager.merge(procImgs.get(0));
			manager.merge(procImgs.get(1));*/
		
		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServicioGestionProcesamientosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private Point getCentroideEmparejado(Point punto, RectStar rec, ArrayList<Point> listaPuntos,
			ArrayList<RectStar> listaRecs) {
		
		// Devuelve el indice de la estrella mas cercana y con semejante brillo dentro de un radio de 20 pixeles
		
		int radioBusqueda = 20; // Numero de pixeles de radio de busqueda. Esto puede ser un parametro a configurar
		int tamRecLim = 2; // El area del rectangulo debera estar entre la mitad y el doble del rectangulo que se pasa
		Point aux;
		double distancia, distanciaMin = 0, brillo, brilloParecido = 0;
		Point elegido = null;
		
		for (int i = 0; i < listaPuntos.size(); i++) {
			aux = listaPuntos.get(i);
			distancia = punto.getDistancia(aux);
			brillo = listaRecs.get(i).getArea();
			if (elegido == null) {
				if (distancia <= radioBusqueda &&
						brillo <= tamRecLim*rec.getArea() && brillo >= rec.getArea()/tamRecLim) {
					distanciaMin = distancia;
					brilloParecido = brillo;
					elegido = aux;
				}
			} else {
				if (distancia <= radioBusqueda &&
						Math.abs(rec.getArea() - brillo) <= Math.abs(rec.getArea() - brilloParecido)
						&& distancia < distanciaMin) {
					brilloParecido = brillo;
					distanciaMin = distancia;
					elegido = aux;
				}
			}
		}
		
		return elegido;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public static Log getLog() {
		return LOG;
	} 

}
