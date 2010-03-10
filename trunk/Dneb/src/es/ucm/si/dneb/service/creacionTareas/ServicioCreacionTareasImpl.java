package es.ucm.si.dneb.service.creacionTareas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DoubleStarCatalog;
import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;
import es.ucm.si.dneb.util.Util;

@Service("servicioCreacionTareas")
public class ServicioCreacionTareasImpl implements ServicioCreacionTareas {

	private static final Log LOG = LogFactory
			.getLog(ServicioCreacionTareas.class);

	@PersistenceContext
	EntityManager manager;

	@Resource
	private GestorDescargas gestorDescargas;

	@Transactional(propagation = Propagation.REQUIRED)
	public void crearTarea(String arInicial, String arFinal, String decInicial,
			String decFinal, double alto, double ancho, double solapamiento,
			String surveyOld, String surveynNew, String formato, String ruta) {

		LOG.info("COMIENZA LA CREACIÓN DE UNA TAREA: ARINI:" + arInicial
				+ "ARFIN" + arFinal + "DECINI" + decInicial + "DECFIN"
				+ decFinal);
		chequeaSiValoresNoNulos(arInicial, arFinal, decInicial, decFinal, alto,
				ancho, solapamiento, surveyOld, surveynNew, formato);

		Date fechaActual;

		Tarea tarea = new Tarea();

		fechaActual = Util.dameFechaActual();

		FormatoFichero formatoFichero;

		try {
			formatoFichero = (FormatoFichero) manager.createNamedQuery(
					"FormatoFichero:dameFormatoPorDescripcion").setParameter(1,
					formato).getSingleResult();
		} catch (NoResultException e) {
			LOG
					.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG
					.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		}

		ArrayList<Survey> surveys = new ArrayList<Survey>();

		try {
			Survey surveyViejo = (Survey) manager.createNamedQuery(
					"Survey:dameSurveyPorDescripcion").setParameter(1,
					surveyOld).getSingleResult();
			surveys.add(surveyViejo);
		} catch (NoResultException e) {
			LOG
					.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG
					.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		}

		try {
			Survey surveyActual = (Survey) manager.createNamedQuery(
					"Survey:dameSurveyPorDescripcion").setParameter(1,
					surveynNew).getSingleResult();
			surveys.add(surveyActual);
		} catch (NoResultException e) {
			LOG
					.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG
					.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		}

		tarea.setSurveys(surveys);

		tarea.setAlto(alto);
		tarea.setAncho(ancho);
		tarea.setArFinal(arFinal);
		tarea.setArInicial(arInicial);
		tarea.setDecInicial(decInicial);
		tarea.setDecFinal(decFinal);
		tarea.setSolpamiento(solapamiento);
		tarea.setFormatoFichero(formatoFichero);
		tarea.setRuta(ruta);
		tarea.setActiva(false);
		tarea.setFinalizada(false);

		tarea.setFechaCreacion(fechaActual);
		tarea.setFechaUltimaActualizacion(fechaActual);
		try {
			manager.persist(tarea);
		} catch (RuntimeException e) {

			LOG.error("NO SE HA PODIDO PERSISTIR LA TAREA");

		}
		List<Imagen> imagens = crearDescargas(tarea);

		tarea.setDescargas(imagens);
		try {
			manager.merge(tarea);
		} catch (RuntimeException e) {

			LOG.error("NO SE HA PODIDO ACTUALIZAR LA TAREA");

		}
		gestorDescargas.anadirHilo(tarea);

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private void chequeaSiValoresNoNulos(String arInicial, String arFinal,
			String decInicial, String decFinal, double alto, double ancho,
			double solapamiento, String surveyOld, String surveynNew,
			String formato) {
		if (arInicial == null || arFinal == null || decInicial == null
				|| decFinal == null || alto <= 0 || ancho <= 0
				|| solapamiento < 0 || surveyOld == null || surveynNew == null
				|| formato == null) {
			LOG.error("PARAMETROS NO VÁLIDOS");
			throw new ServicioCreacionTareasException(
					"Parámetros ilegarles al crear tarea");
		}
		LOG.info("CHEQUEO DE VALORES NULOS SUPERADO");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Imagen> crearDescargas(Tarea tarea) {

		ArrayList<Imagen> imagens = new ArrayList<Imagen>();

		String ariniS = tarea.getArInicial();
		Double arini = Double.parseDouble(ariniS);

		String deciniS = tarea.getDecInicial();
		Double decini = Double.parseDouble(deciniS);

		String arfinS = tarea.getArFinal();
		Double arfin = Double.parseDouble(arfinS);

		String decfinS = tarea.getDecFinal();
		Double decfin = Double.parseDouble(decfinS);

		Double alto = tarea.getAlto();
		Double ancho = tarea.getAncho();

		Double ar = arini;
		Double dec = decini;

		Double solap = tarea.getSolpamiento() / 100;
		Double anchoreal = new Double(1D);

		LOG.debug("DATOS CREADOS CORRECTAMENTE");

		while (ar <= arfin) {

			while (dec <= decfin) {

				List<Survey> surveys = tarea.getSurveys();

				LOG.debug("OBTENIDAS TODAS LAS LISTAS DE SURVEYS");

				for (Survey survey : surveys) {

					Imagen imagen = new Imagen();
					imagen.setAscensionRecta(ar.toString());
					imagen.setDeclinacion(dec.toString());
					imagen.setDescargada(false);
					/**
					 * TODO OJO QUE ESTO ES UN CAMBIO IMPORTANTE HAY QUE PROBAR
					 * SI FUNCIONA
					 **/
					imagen.setRutaFichero(Util.creaRuta(tarea.getRuta(), survey
							.getDescripcion(), ar.toString(), dec.toString(),
							tarea.getFormatoFichero().getAlias()));
					imagen.setSurvey(survey);
					imagen.setTarea(tarea);
					imagen.setAncho((ancho * (Math
							.cos((dec * 2.0 * Math.PI) / 360.0))));

					manager.persist(imagen);

					imagens.add(imagen);

					LOG.debug("AÑADIDA LA DESCARGA DEL SURVEY:"
							+ imagen.getSurvey().getDescripcion());

				}

				dec = calculaDec(decini, decfin, alto, dec, solap);

			}
			dec = decini;
			anchoreal = ancho * (Math.cos((dec * 2.0 * Math.PI) / 360.0));
			ar = calculaAr(ar, solap, anchoreal);

		}
		return imagens;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private Double calculaAr(Double ar, Double solap, Double anchoreal) {

		ar = ar + (anchoreal) - (anchoreal * solap);

		LOG.debug("CACULA AR :" + ar.toString());
		return ar;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private Double calculaDec(Double decini, Double decfin, Double alto,
			Double dec, Double solap) {
		if (decini > decfin) {
			dec = dec - alto + (alto * solap);
		} else {
			dec = dec + alto - (alto * solap);
		}
		LOG.debug("CACULA DEC :" + dec.toString());
		return dec;
	}

	public void setGestorHilos(GestorDescargas gestorDescargas) {
		this.gestorDescargas = gestorDescargas;
	}

	public GestorDescargas getGestorHilos() {
		return gestorDescargas;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearTarea(List<DoubleStarCatalog> dscs) {
		// TODO Auto-generated method stub
		Tarea tarea = new Tarea();

		tarea.setActiva(false);
		tarea.setAlias("CALCDIST");
		tarea.setAlto(4);
		tarea.setAncho(4);
		tarea.setArFinal("0");
		tarea.setArInicial("0");
		tarea.setDecFinal("0");
		tarea.setDecInicial("0");

		tarea
				.setDescripcion("TAREA DE DESCARGA DE IMAGENES PARA CALCULO DE DISTANCIA");
		tarea.setFechaCreacion(Util.dameFechaActual());
		tarea.setFechaUltimaActualizacion(Util.dameFechaActual());

		tarea.setFinalizada(false);

		FormatoFichero formatoFichero;

		try {
			formatoFichero = (FormatoFichero) manager.createNamedQuery(
					"FormatoFichero:dameFormatoPorDescripcion").setParameter(1,
					"fits").getSingleResult();
		} catch (NoResultException e) {
			LOG
					.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG
					.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		}

		tarea.setFormatoFichero(formatoFichero);
		tarea.setRuta("d:\\");
		// tarea.setSolpamiento(solpamiento);

		ArrayList<Survey> surveys = new ArrayList<Survey>();

		try {
			Survey surveyViejo = (Survey) manager.createNamedQuery(
					"Survey:dameSurveyPorDescripcion").setParameter(1,
					"poss2ukstu_blue").getSingleResult();
			surveys.add(surveyViejo);
		} catch (NoResultException e) {
			LOG
					.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG
					.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		}

		tarea.setSurveys(surveys);
		try {
			manager.persist(tarea);
		} catch (Exception e) {
			LOG.error("Problema persistiendo tarea" + e.getCause()
					+ e.getStackTrace());
			throw new ServicioCreacionTareasException(
					"Problema persistiendo tarea" + e.getCause()
							+ e.getStackTrace());
		}
		// tarea.setDescargas(imagens);

		for (DoubleStarCatalog dsc : dscs) {

			Imagen imagen = new Imagen();

			imagen.setAncho(tarea.getAncho());
			imagen.setAscensionRecta(dsc.getArcsecondCoordinates2000()
					.substring(0, 10));
			imagen.setDeclinacion(dsc.getArcsecondCoordinates2000().substring(
					10, 18));
			imagen.setDescargada(false);
			// imagen.setFechaDescarga(fechaFinalizacion);
			// imagen.setProcesamientoImagen(procImagen);
			imagen.setRutaFichero(Util.creaRuta(tarea.getRuta(), surveys.get(0)
					.getDescripcion(), dsc.getArcsecondCoordinates2000()
					.substring(0, 10), dsc.getArcsecondCoordinates2000()
					.substring(10, 18), tarea.getFormatoFichero().getAlias()));
			imagen.setSurvey(surveys.get(0));
			imagen.setTarea(tarea);
			try {
				manager.persist(imagen);

			} catch (Exception e) {
				LOG.error("Problema persistiendo imagen" + e.getCause()
						+ e.getStackTrace());
				throw new ServicioCreacionTareasException(
						"Problema persistiendo imagen" + e.getCause()
								+ e.getStackTrace());
			}

		}

	}

}
