package es.ucm.si.dneb.service.calculoPosicion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DoubleStarCatalog;
import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ParamImg;
import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.busquedaDobles.ServiceBusquedaDobles;
import es.ucm.si.dneb.service.consultarCatalogo.ServicioConsultaCatalogo;
import es.ucm.si.dneb.service.image.centroid.CalculateBookCentroid;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.RectStar;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.Distance;
import es.ucm.si.dneb.service.math.MathService;

@Service("serviceCalculoPosicion")
public class ServiceCalculoPosicionImpl implements ServiceCalculoPosicion{
	
	private static final Log LOG = LogFactory
	.getLog(ServiceCalculoPosicionImpl.class);

	@PersistenceContext
	EntityManager manager;
	
	@Resource
	private ServicioConsultaCatalogo servicioConsultaCatalogo;
	
	@Resource
	private MathService mathService;
	
	@Resource
	private ServiceBusquedaDobles serviceBusquedaDobles;
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void calcularPosicion(ProcImagen pi) {
		
		//TODO
		//Saco los parámetro de la imagen
		List<ParamImg> paramsImg =pi.getParams();
		
		//Busco los datos de la estrella binaria a buscar
		
		Imagen imagen=pi.getImagen();
		
		double ar=Double.parseDouble(imagen.getAscensionRecta());
		double dec=Double.parseDouble(imagen.getDeclinacion());
		
		double param1=ar+0.0000000000001;
		double param2=ar-0.0000000000001;
		
		double param3=dec+0.0000000000001;
		double param4=dec-0.0000000000001;
		
		
		List<DoubleStarCatalog> dscList= manager.createQuery("select dsc from DoubleStarCatalog dsc where dsc.ascRecGrados<? and dsc.ascRecGrados>?  and dsc.decGrados<? and dsc.decGrados>?").setParameter(1, param1).setParameter(2,param2 ).setParameter(3,param3 ).setParameter(4,param4 ).getResultList();
		
		if(dscList.size()<1){
			LOG.error("NO SE HA PODIDO LOCALIZAR LA INFORMACIÓN EN EL CATALOGO");
			return;
		}
		
		dscList.get(0);
		
		//Busco las estrellas
		LOG.info("PROCESAMIENTO DE CALCULO DE POSICION");
		
		String filename1 = pi.getImagen().getRutaFichero();
		
		LOG.info("FICHERO : "+filename1);
		
		Fits imagenFITS;
		List<RectStar> recStars= new ArrayList<RectStar>();
		try {
			imagenFITS = new Fits(new File(filename1));			
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l = new LectorImageHDU(imageHDU, filename1);
			StarFinder sf = new StarFinder();
			List<ParamProcTarea> paramProcTareas = pi.getTareaProcesamiento().getParametros();
			double brillo = 0, umbral = 0;
			for (int i = 0; i < paramProcTareas.size(); i++) {
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
					brillo = paramProcTareas.get(i).getValorNum();
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
					umbral = paramProcTareas.get(i).getValorNum();
			}
			sf.buscarEstrellas(l, new Float(brillo), new Float(umbral));
			LOG.info("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			recStars=sf.getRecuadros();
			
			//FILTRAR POR BRILLO
			
			//CALCULAR CENTROIDES
			ArrayList<Point> centroides = new ArrayList<Point>();
			CalculateBookCentroid cc = new CalculateBookCentroid();
			Point cent;
			

			int nRecuadros=recStars.size();
			
			for (int i = 0; i < nRecuadros; i++) {
				
				cent = cc.giveMeTheCentroid(l.getPorcionImagen(recStars.get(i).getxLeft(), recStars.get(i).getyTop(), 
						recStars.get(i).getWidth(), recStars.get(i).getHeight()));
				
				
				cent.setX(recStars.get(i).getxLeft() + cent.getX());
				cent.setY(recStars.get(i).getyTop() + cent.getY());
				
				
				LOG.info("\r\n\r\nCentroide " + i + " de la imagen 1:\r\n\tX: " + cent.getX() + "\r\n\tY: " + cent.getY());
				LOG.info("\r\n\r\nRectángulo " + i + " de la imagen 1:\r\n\txLeft: " + recStars.get(i).getxLeft()
						+ "\r\n\txRight: " + recStars.get(i).getxRight()
						+ "\r\n\tyTop: " + recStars.get(i).getyTop()
						+ "\r\n\tyBot: " + recStars.get(i).getyBot());

				
				centroides.add(cent);

			}
			
			LOG.debug("");
			
			//PASAR DE PÍXELES A COORDENADAS
			
			ArrayList<DecimalCoordinate> centroidesDC= new ArrayList<DecimalCoordinate>();
			
			
			for(Point pointIter: centroides){
				LOG.debug("ANCHO: "+l.getWidth()+" ALTO: "+ l.getHeight()+" X: "+ pointIter.getX()+" Y: "+ pointIter.getY());
				 DecimalCoordinate dc= serviceBusquedaDobles.pixelToCoordinatesConverter(imagen, l.getWidth(), l.getHeight(), pointIter.getX(), pointIter.getY());
				 LOG.debug("AR: "+dc.getAr() +" DEC: "+dc.getDec());
				 centroidesDC.add(dc);
			}
			
			//CALCULAR DISTANCIAS ENTRE TODOS LOS CENTROIDES
			
			List<Distance> distancesList= new ArrayList<Distance>();
			
			for(DecimalCoordinate dc1 : centroidesDC){
				for(DecimalCoordinate dc2 : centroidesDC){
					
					Distance distanceAux=this.mathService.calculateDecimalDistance(dc1.getAr(), dc1.getDec(), dc2.getAr(), dc2.getDec());
					distanceAux.setPoint1(dc1);
					distanceAux.setPoint2(dc2);
					
					distancesList.add(distanceAux);
				}
			}
			
			
			
			
			
			
		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Calculo los centros
		
		//Filtro de candidatas por brillo
		
		//Ojo a los colores
		
		//Sobre las estrellas calculo las distancias por parejas???
		
		
	}


	public void setMathService(MathService mathService) {
		this.mathService = mathService;
	}


	public MathService getMathService() {
		return mathService;
	}


	public void setServicioConsultaCatalogo(ServicioConsultaCatalogo servicioConsultaCatalogo) {
		this.servicioConsultaCatalogo = servicioConsultaCatalogo;
	}


	public ServicioConsultaCatalogo getServicioConsultaCatalogo() {
		return servicioConsultaCatalogo;
	}


	public void setServiceBusquedaDobles(ServiceBusquedaDobles serviceBusquedaDobles) {
		this.serviceBusquedaDobles = serviceBusquedaDobles;
	}


	public ServiceBusquedaDobles getServiceBusquedaDobles() {
		return serviceBusquedaDobles;
	}

}
