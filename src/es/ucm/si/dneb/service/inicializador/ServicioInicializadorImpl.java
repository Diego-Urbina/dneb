package es.ucm.si.dneb.service.inicializador;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.CargaDatos;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.domain.TipoInformacionRelevante;
import es.ucm.si.dneb.domain.TipoParametro;
import es.ucm.si.dneb.domain.TipoProcesamiento;

import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareasException;
import es.ucm.si.dneb.service.downloadImage.ServiceDownloadImageException;
import es.ucm.si.dneb.util.Util;

@Service("servicioInicializador")
public class ServicioInicializadorImpl implements ServicioInicializador {

	@PersistenceContext
	EntityManager manager;

	private static final Log LOG = LogFactory
			.getLog(ServicioInicializadorImpl.class);

	final static String THEME = "Test";

	@Transactional(propagation = Propagation.REQUIRED)
	public void inicializar() {

		LOG.info("CONFIGURATION IS GOING TO BE VALIDATED");

		/* Inicialización de surveys */

		if (manager.find(Survey.class, 1L) == null) {

			Survey surv1 = new Survey();
			surv1.setIdSurvey(1L);
			surv1.setDescripcion("poss1_red");
			manager.persist(surv1);
			LOG.info("CREATING SURVEY: " + surv1);
		}

		if (manager.find(Survey.class, 2L) == null) {

			Survey surv2 = new Survey();
			surv2.setIdSurvey(2L);
			surv2.setDescripcion("poss1_blue");
			manager.persist(surv2);
			LOG.info("CREATING SURVEY: " + surv2);
		}

		if (manager.find(Survey.class, 3L) == null) {

			Survey surv3 = new Survey();
			surv3.setIdSurvey(3L);
			surv3.setDescripcion("poss2ukstu_blue");
			manager.persist(surv3);
			LOG.info("CREATING SURVEY: " + surv3);
		}

		if (manager.find(Survey.class, 4L) == null) {
			Survey surv4 = new Survey();
			surv4.setIdSurvey(4L);
			surv4.setDescripcion("poss2ukstu_ir");
			manager.persist(surv4);
			LOG.info("CREATING SURVEY: " + surv4);
		}

		if (manager.find(Survey.class, 5L) == null) {
			Survey surv5 = new Survey();
			surv5.setIdSurvey(5L);
			surv5.setDescripcion("poss2ukstu_red");
			manager.persist(surv5);
			LOG.info("CREATING SURVEY: " + surv5);
		}

		/* Cargas de formatos de fichero */

		if (manager.find(FormatoFichero.class, 1L) == null) {
			FormatoFichero ff1 = new FormatoFichero();
			ff1.setAlias("fits");
			ff1.setIdFormatoFichero(1L);
			ff1.setDescription("fits");
			manager.persist(ff1);
			LOG.info("CREATING FORMATO FICHERO: " + ff1);
		}

		if (manager.find(FormatoFichero.class, 2L) == null) {
			FormatoFichero ff2 = new FormatoFichero();
			ff2.setAlias("jpeg");
			ff2.setIdFormatoFichero(2L);
			ff2.setDescription("jpeg");
			manager.persist(ff2);
			LOG.info("CREATING FORMATO FICHERO: " + ff2);
		}

		/* Tipos de procesamientos */

		if (manager.find(TipoProcesamiento.class, 1L) == null) {

			TipoProcesamiento tp1 = new TipoProcesamiento();
			tp1.setAlias("Procesamiento busqueda dobles");
			tp1.setDescripcion("Procesamiento busqueda dobles");
			tp1.setIdTipoProcesamiento(1L);
			manager.persist(tp1);
			LOG.info("CREATING TIPO PROCESAMIENTO: " + tp1);

		}

		if (manager.find(TipoProcesamiento.class, 2L) == null) {

			TipoProcesamiento tp2 = new TipoProcesamiento();
			tp2.setAlias("Procesamiento calculo distancia");
			tp2.setDescripcion("Procesamiento calculo distancia");
			tp2.setIdTipoProcesamiento(2L);
			manager.persist(tp2);
			LOG.info("CREATING TIPO PROCESAMIENTO: " + tp2);

		}
		/* Tipos de parámetros */

		if (manager.find(TipoParametro.class, 1L) == null) {

			TipoParametro tp1 = new TipoParametro();
			tp1.setAlias("Brillo");
			
			tp1.setDescripcion("Brillo de busqueda de estrellas ");
			tp1.setIdTipoParametro(1L);
			manager.persist(tp1);
			LOG.info("CREATING TIPO PARAMETRO: " + tp1);

		}

		if (manager.find(TipoParametro.class, 2L) == null) {

			TipoParametro tp2 = new TipoParametro();
			tp2.setAlias("Umbral");
			tp2.setDescripcion("Umbral de busqueda de estrellas");
			tp2.setIdTipoParametro(2L);
			manager.persist(tp2);
			LOG.info("CREATING TIPO PARAMETRO: " + tp2);

		}
/*
		if (manager.find(TipoParametro.class, 3L) == null) {

			TipoParametro tp3 = new TipoParametro();
			tp3.setAlias("Limite Candidatos");
			tp3.setDescripcion("Límite de candidatos de cálculo distancia");
			tp3.setIdTipoParametro(3L);
			manager.persist(tp3);
			LOG.info("CREATING TIPO PARAMETRO: " + tp3);

		}

		if (manager.find(TipoParametro.class, 4L) == null) {

			TipoParametro tp4 = new TipoParametro();
			tp4.setAlias("Máximo numero de estrellas");
			tp4.setDescripcion("Máximo número de estrellas de busqueda de cálculo distancia");
			tp4.setIdTipoParametro(4L);
			manager.persist(tp4);
			LOG.info("CREATING TIPO PARAMETRO: " + tp4);

		}

		if (manager.find(TipoParametro.class, 5L) == null) {

			TipoParametro tp5 = new TipoParametro();
			tp5.setAlias("Margen Ángulo");
			tp5.setDescripcion("Margen Ángulo de cálculo distancia");
			tp5.setIdTipoParametro(5L);
			manager.persist(tp5);
			LOG.info("CREATING TIPO PARAMETRO: " + tp5);

		}

		if (manager.find(TipoParametro.class, 6L) == null) {

			TipoParametro tp6 = new TipoParametro();
			tp6.setAlias("Margen Distancia");
			tp6.setDescripcion("Margen Distancia de busqueda de cálculo distancia");
			tp6.setIdTipoParametro(6L);
			manager.persist(tp6);
			LOG.info("CREATING TIPO PARAMETRO: " + tp6);

		}

		if (manager.find(TipoParametro.class, 7L) == null) {

			TipoParametro tp7 = new TipoParametro();
			tp7.setAlias("Distancia Minima");
			tp7.setDescripcion("Distancia Minima de busqueda de cálculo distancia");
			tp7.setIdTipoParametro(7L);
			manager.persist(tp7);
			LOG.info("CREATING TIPO PARAMETRO: " + tp7);

		}*/

		/* Tipos de informaciones relevantes */

		if (manager.find(TipoInformacionRelevante.class, 1L) == null) {

			TipoInformacionRelevante tir1 = new TipoInformacionRelevante();
			tir1.setId(1L);
			tir1.setAlias("CALC DOBLES");
			tir1.setDescription("BUSQUEDA ESTRELLA DOBLE");
			manager.persist(tir1);
			LOG.info("CREATING TIPO INFORMACION RELEVANTE: " + tir1);

		}

		if (manager.find(TipoInformacionRelevante.class, 2L) == null) {

			TipoInformacionRelevante tir2 = new TipoInformacionRelevante();
			tir2.setId(2L);
			tir2.setAlias("CALC DIST");
			tir2.setDescription("INFO RELEVANTE DEL CALCULO DE DISTANCIA USANDO EL WDSC");
			manager.persist(tir2);
			LOG.info("CREATING TIPO INFORMACION RELEVANTE: " + tir2);

		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void chequeoConsistencia() {

		List<Tarea> tareas = manager.createNamedQuery(
				"Tarea:DameTodasTareasActivas").getResultList();

		for (Tarea tarea : tareas) {

			if (tarea.isActiva() == true) {
				tarea.setActiva(false);
				manager.merge(tarea);
			}

		}

		List<ProcTarea> procTareas = manager.createNamedQuery(
				"ProcTarea.getTareaProcesamientoActivo").getResultList();

		for (ProcTarea procesamiento : procTareas) {

			if (procesamiento.isActiva()) {
				procesamiento.setActiva(false);
				manager.merge(procesamiento);

			}
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eleminarTareasHistoricas(Date fecha) {

		List<Tarea> tareas = manager
				.createNamedQuery("Tarea:DameTodasTareasActualizadasAntesFecha")
				.setParameter(1, fecha).getResultList();

		for (Tarea tarea : tareas) {
			if (tarea.isActiva() == false && tarea.isFinalizada() == true) {

				List<Imagen> imagens = tarea.getDescargas();

				for (Imagen imagen : imagens) {
					manager.remove(imagen);
				}

				manager.remove(tarea);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void generarTareaSobreDatosManuales() {

		LOG.info("GENERANDO DATOS MANUALES DE DESCARGA");

		List<CargaDatos> cargaDatos = (List<CargaDatos>) manager
				.createNamedQuery(
						"CargaDatos:dameTodosPuntosRelevantesNoProcesados")
				.getResultList();

		if (cargaDatos.size() == 0) {
			return;
		}

		ResourceBundle resource = ResourceBundle
				.getBundle("es.ucm.si.dneb.resources.resources");
		String survey1 = resource.getString("survey1");
		String survey2 = resource.getString("survey2");

		ArrayList<Survey> surveys = new ArrayList<Survey>();
		Survey surveyold = (Survey) manager
				.createNamedQuery("Survey:dameSurveyPorDescripcion")
				.setParameter(1, survey1).getSingleResult();
		Survey surveynew = (Survey) manager
				.createNamedQuery("Survey:dameSurveyPorDescripcion")
				.setParameter(1, survey2).getSingleResult();

		surveys.add(surveyold);
		surveys.add(surveynew);

		String ruta = resource.getString("directoriodescargas");
		String ancho = resource.getString("anchopordefecto");
		String alto = resource.getString("altopordefecto");
		String solapamiento = resource.getString("solpamientopordefecto");

		String formatoFichero = resource.getString("formatodefecto");

		FormatoFichero formato;

		try {
			formato = (FormatoFichero) manager
					.createNamedQuery(
							"FormatoFichero:dameFormatoPorDescripcion")
					.setParameter(1, formatoFichero).getSingleResult();
		} catch (NoResultException e) {
			LOG.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		}

		Tarea tarea = new Tarea();
		tarea.setActiva(false);
		tarea.setAlto(Double.parseDouble(alto));
		tarea.setAncho(Double.parseDouble(ancho));
		tarea.setArFinal("0");
		tarea.setArInicial("0");
		tarea.setDecInicial("0");
		tarea.setDecFinal("0");
		tarea.setFechaCreacion(Util.dameFechaActual());
		tarea.setFechaUltimaActualizacion(Util.dameFechaActual());
		tarea.setFinalizada(false);
		tarea.setFormatoFichero(formato);
		tarea.setRuta(ruta);
		tarea.setSolpamiento(Double.parseDouble(solapamiento));
		tarea.setSurveys(surveys);
		/** TODO **/
		manager.persist(tarea);

		ArrayList<Imagen> imagens = new ArrayList<Imagen>();

		for (CargaDatos punto : cargaDatos) {

			for (Survey survey : surveys) {

				Imagen imagen = new Imagen();
				imagen.setAncho(Double.parseDouble(ancho));
				String ar = new Double(punto.getAscencionRecta()).toString();
				imagen.setAscensionRecta(ar);
				String dec = new Double(punto.getDeclinacion()).toString();
				imagen.setDeclinacion(dec);
				imagen.setDescargada(false);
				imagen.setRutaFichero(Util.creaRuta(tarea.getRuta(),
						survey.getDescripcion(), ar.toString(), dec.toString(),
						tarea.getFormatoFichero().getAlias()));
				imagen.setSurvey(survey);
				imagen.setTarea(tarea);

				manager.persist(imagen);

				imagens.add(imagen);

			}
			punto.setProcesado(true);
			manager.persist(punto);
		}
		tarea.setDescargas(imagens);

		manager.merge(tarea);

		LOG.info("FINALIZADA LA GENERACIÓN DE DESCARGAS MANUALES");

	}

	@Override
	public boolean testConnection() {
		try{
			final HttpClient httpclient = new DefaultHttpClient();
			
			final List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>(0);
			UrlEncodedFormEntity entity = null;
			
			entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			
			LOG.info("Parámetros configurados correctamente");
			final HttpPost httppost = new HttpPost(
					"http://archive.stsci.edu/cgi-bin/dss_search");

			httppost.setEntity(entity);

			HttpResponse response = null;

			response = httpclient.execute(httppost);

			LOG.info("HTTPPOST EJECUTADO SATISFACTORIAMENTE");

			final HttpEntity resEnt = response.getEntity();
			if (resEnt != null) {
				BufferedInputStream bis = null;
				
				LOG.info("Downloading information");
				bis = new BufferedInputStream(resEnt.getContent());
			}	

			return true;
			
		}catch(Exception e){
			return false;
		}

	}

}
