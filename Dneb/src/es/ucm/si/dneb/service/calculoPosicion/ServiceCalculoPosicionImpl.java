package es.ucm.si.dneb.service.calculoPosicion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import es.ucm.si.dneb.domain.InformacionRelevante;
import es.ucm.si.dneb.domain.ParamImg;
import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.domain.TipoInformacionRelevante;
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
import es.ucm.si.dneb.util.Util;

@Service("serviceCalculoPosicion")
public class ServiceCalculoPosicionImpl implements ServiceCalculoPosicion {

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

		/*
		 * 
		 * MOSTRAR ESTADÍSTICAS DE RESULTADOS DEL PROCESAMIENTO EN QUÉ CASOS SE
		 * ENCUENTRAN DATOS RELEVANTES Y EN CUALES NO REALIZAR APROX POR
		 * VELOCIDAD EN CASO DE TENERLA USAR MEDIDAS ESTADÍSTICAS PARA PODER
		 * AJUSTAR LOS PARÁMETROS EN CASO DE SER MUY BRILLANTE SE PUEDE DAR POR
		 * PERDIDO ESE CASO SEGUN LA VELOCIDAD SE PUEDE CONTEMPLAR QUE CAMBIE EL
		 * MARGEN
		 * 
		 * Si están muy cerca entonces mejor o buscar en el 2mass o subir el
		 * umbral para que salgán separadas.
		 * 
		 * SIN SON MUY BRILLANTES AJUSTAR PARÁMETROS DE OTRA FORMA, EN CASO DE
		 * QUE SEAN MUY BRILLANTES MIRAR CON EL CATALOGO 2MASS
		 */

		LOG
				.info("PROCESAMIENTO DE CALCULO DE POSICION INFO:  "
						+ pi.toString());
		// TODO
		// Saco los parámetro de la imagen
		List<ParamImg> paramsImg = pi.getParams();

		List<ParamProcTarea> paramProcTareas = pi.getTareaProcesamiento()
				.getParametros();
		double brillo = 0, umbral = 0;
		for (int i = 0; i < paramProcTareas.size(); i++) {
			if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
				brillo = paramProcTareas.get(i).getValorNum();
			if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
				umbral = paramProcTareas.get(i).getValorNum();
		}

		// Busco los datos de la estrella binaria a buscar

		Imagen imagen = pi.getImagen();
		
		
		algotirmoCalculoPosicion(brillo, umbral, imagen);

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Point> algotirmoCalculoPosicion(double brillo, double umbral,
			Imagen imagen) {
		
		
		ArrayList<Point> points = new ArrayList<Point>();
		/*
		 * MARGEN PARA PODER HACER CONSULTAS SOBRE DOUBLES EN BASE DE DATOS YA
		 * QUE EL OPERADOR = NO ESTÁ DISPONIBLE
		 */
		double ar = Double.parseDouble(imagen.getAscensionRecta());
		double dec = Double.parseDouble(imagen.getDeclinacion());

		double param1 = ar + 0.0000000000001;
		double param2 = ar - 0.0000000000001;

		double param3 = dec + 0.0000000000001;
		double param4 = dec - 0.0000000000001;

		List<DoubleStarCatalog> dscList = manager
				.createQuery(
						"select dsc from DoubleStarCatalog dsc where dsc.ascRecGrados<? and dsc.ascRecGrados>?  and dsc.decGrados<? and dsc.decGrados>?")
				.setParameter(1, param1).setParameter(2, param2).setParameter(
						3, param3).setParameter(4, param4).getResultList();

		if (dscList.size() < 1) {
			LOG
					.error("NO SE HA PODIDO LOCALIZAR LA INFORMACIÓN EN EL CATALOGO");
			return points;
		}

		DoubleStarCatalog dsc = dscList.get(0);

		// Busco las estrellas
		LOG.info("PROCESAMIENTO DE CALCULO DE POSICION");

		String filename1 = imagen.getRutaFichero();

		LOG.info("FICHERO : " + filename1);

		Fits imagenFITS;
		List<RectStar> recStars = new ArrayList<RectStar>();
		try {

			/* TODO ALGORITMO PARA PROBAR LA CERCANÍA DE DOS ESTRELLAS */
			/*
			 * if(dsc.getLastSeparation()){
			 * 
			 * }
			 */

			imagenFITS = new Fits(new File(filename1));
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l = new LectorImageHDU(imageHDU, filename1);
			StarFinder sf = new StarFinder();

			boolean sinRelevantes = true;

			while (sinRelevantes) {

				LOG
						.info("SE PROCEDE A EJECUTAR EL ALGORITMO CON LOS SIGUIENTES PARÁMETROS: BRILLO: "
								+ brillo + "UMBRAL: " + umbral);

				sf.buscarEstrellas(l, new Float(brillo), new Float(umbral));
				LOG.info("Numero de estrellas encontradas: "
						+ sf.getNumberOfStars());
				recStars = sf.getRecuadros();

				// CALCULAR CENTROIDES
				ArrayList<Point> centroides = new ArrayList<Point>();
				CalculateBookCentroid cc = new CalculateBookCentroid();
				Point cent;

				int nRecuadros = recStars.size();

				for (int i = 0; i < nRecuadros; i++) {

					cent = cc.giveMeTheCentroid(l.getPorcionImagen(recStars
							.get(i).getxLeft(), recStars.get(i).getyTop(),
							recStars.get(i).getWidth(), recStars.get(i)
									.getHeight()));

					cent.setX(recStars.get(i).getxLeft() + cent.getX());
					cent.setY(recStars.get(i).getyTop() + cent.getY());

					LOG.info("\r\n\r\nCentroide " + i
							+ " de la imagen 1:\r\n\tX: " + cent.getX()
							+ "\r\n\tY: " + cent.getY());
					LOG.info("\r\n\r\nRectángulo " + i
							+ " de la imagen 1:\r\n\txLeft: "
							+ recStars.get(i).getxLeft() + "\r\n\txRight: "
							+ recStars.get(i).getxRight() + "\r\n\tyTop: "
							+ recStars.get(i).getyTop() + "\r\n\tyBot: "
							+ recStars.get(i).getyBot());

					centroides.add(cent);

				}

				LOG.info("PASANDO DE PÍXELES A COORDENADAS");

				// PASAR DE PÍXELES A COORDENADAS

				HashMap<DecimalCoordinate, Point> dcToPoint = new HashMap<DecimalCoordinate, Point>();

				ArrayList<DecimalCoordinate> centroidesDC = new ArrayList<DecimalCoordinate>();

				for (Point pointIter : centroides) {
					LOG.debug("ANCHO: " + l.getWidth() + " ALTO: "
							+ l.getHeight() + " X: " + pointIter.getX()
							+ " Y: " + pointIter.getY());
					DecimalCoordinate dc = serviceBusquedaDobles
							.pixelToCoordinatesConverter(imagen, l.getWidth(),
									l.getHeight(), pointIter.getX(), pointIter
											.getY());
					LOG.debug("AR: " + dc.getAr() + " DEC: " + dc.getDec());
					centroidesDC.add(dc);

					dcToPoint.put(dc, pointIter);
				}

				// CALCULAR DISTANCIAS ENTRE TODOS LOS CENTROIDES

				List<Distance> distancesList = new ArrayList<Distance>();

				for (DecimalCoordinate dc1 : centroidesDC) {
					for (DecimalCoordinate dc2 : centroidesDC) {

						Distance distanceAux = this.mathService
								.calculateDecimalDistance(dc1.getAr(), dc1
										.getDec(), dc2.getAr(), dc2.getDec());

						distanceAux.setPoint1(dc1);
						distanceAux.setPoint2(dc2);

						LOG.info("DISTANCE INFO: " + distanceAux.toString());

						distancesList.add(distanceAux);
					}
				}

				for (Distance dist : distancesList) {

					double sep = dsc.getLastSeparation();
					double ang = dsc.getLastPosAnges();

					if (((sep * 0.85) <= dist.getDistanceSeconds() && dist
							.getDistanceSeconds() <= (sep * 1.15))
							&& ((ang * 0.85) <= dist.getAngle() && dist
									.getAngle() <= (ang * 1.15))) {

						LOG.info("PUNTOS DENTRO DE RANGO:" + dist.toString());

						/*
						 * ESTUDIAR SI LA POSICIÓN EN LA QUE HAN SIDO
						 * ENCONTRADAS ES CERCANA A LA ANTERIOR DONDE SE SUPONE
						 * QUE ESTÁN
						 */

						/* ESTUDIO DE FILTRADO POR BRILLO */

						/**/

						InformacionRelevante ir = new InformacionRelevante();
						ir
								.setDescription("CALCULO DISTANCIA: INFO DISTANCIA Y PUNTOS"
										+ dist + "INFO DSC" + dsc.toString());
						ir.setFecha(Util.dameFechaActual());
						List<Imagen> imagenes = new ArrayList<Imagen>();
						imagenes.add(imagen);
						ir.setImagenes(imagenes);

						ir.setTipoInformacionRelevante(manager.find(
								TipoInformacionRelevante.class, 2L));

						manager.persist(ir);
						

						points.add(dcToPoint.get(dist.getPoint1()));
						points.add(dcToPoint.get(dist.getPoint2()));

						sinRelevantes = false;
					}

				}
				if (umbral < brillo) {
					brillo = brillo - (brillo * 0.04);

					LOG
							.info("NO SE HAN ENCONTRADO DATOS RELEVANTES, AJUSTANDO PARÁMETROS: BRILLO: "
									+ brillo + "UMBRAL: " + umbral);
				} else {
					sinRelevantes = false;
				}

			}

		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return points;
	}

	public void setMathService(MathService mathService) {
		this.mathService = mathService;
	}

	public MathService getMathService() {
		return mathService;
	}

	public void setServicioConsultaCatalogo(
			ServicioConsultaCatalogo servicioConsultaCatalogo) {
		this.servicioConsultaCatalogo = servicioConsultaCatalogo;
	}

	public ServicioConsultaCatalogo getServicioConsultaCatalogo() {
		return servicioConsultaCatalogo;
	}

	public void setServiceBusquedaDobles(
			ServiceBusquedaDobles serviceBusquedaDobles) {
		this.serviceBusquedaDobles = serviceBusquedaDobles;
	}

	public ServiceBusquedaDobles getServiceBusquedaDobles() {
		return serviceBusquedaDobles;
	}

}
