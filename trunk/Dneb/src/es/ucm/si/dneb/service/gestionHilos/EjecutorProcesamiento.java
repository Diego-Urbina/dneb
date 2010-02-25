package es.ucm.si.dneb.service.gestionHilos;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.service.busquedaDobles.ServiceBusquedaDobles;
import es.ucm.si.dneb.service.calculoPosicion.ServiceCalculoPosicion;

@Service("ejecutorProcesamiento")
@Scope("prototype")
@Transactional(propagation = Propagation.SUPPORTS)
public class EjecutorProcesamiento implements EjecutorTarea<ProcTarea> {

	@Resource
	private ServiceBusquedaDobles serviceBusquedaDobles;

	@Resource
	private ServiceCalculoPosicion serviceCalculoPosicion;

	private ProcTarea procTarea;
	private Long tareaProcesamientoId;

	private static final Log LOG = LogFactory
			.getLog(EjecutorProcesamiento.class);

	@PersistenceContext
	EntityManager manager;

	private List<ProcImagen> getDownloads() {

		return manager.createNamedQuery(
				"ProcTarea.getAllProcesamientoImagen").setParameter(1,
				this.procTarea).getResultList();

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void ejecutar(Interrumpible inter) {

		List<ProcImagen> imagens = getDownloads();

		if (this.procTarea.getTipoProcesamiento()
				.getIdTipoProcesamiento() == 1) {

			for (ProcImagen imagen : imagens) {

				if (inter.continuar()) {

					ProcImagen procImg1 = manager.find(
							ProcImagen.class, imagen.getId());
					// Buscamos la gemela pero con otro survey

					List<ProcImagen> procImgs = manager
							.createNamedQuery(
									"ProcTarea.getImagenesGemelas")
							.setParameter(1, procImg1.getTareaProcesamiento())
							.setParameter(2,
									procImg1.getImagen().getAscensionRecta())
							.setParameter(3,
									procImg1.getImagen().getDeclinacion())
							.getResultList();
					if ((!procImgs.get(0).isFinalizada())
							&& (!procImgs.get(1).isFinalizada())) {
						serviceBusquedaDobles.iniciarProcesamiento(procImgs);

					}

				} else {
					break;
				}

			}

		} else {
			for (ProcImagen imagen : imagens) {

				if (inter.continuar()) {

					serviceCalculoPosicion.calcularPosicion(imagen);

				} else {
					break;
				}

			}
		}
		if (inter.continuar()) {
			procTarea.setFinalizada(true);
			manager.merge(procTarea);
		}

	}

	@Override
	public ProcTarea getCore() {
		return this.procTarea;
	}

	@Override
	public Long getId() {
		return this.tareaProcesamientoId;
	}

	@Override
	public void setCore(ProcTarea core) {
		this.procTarea = core;
	}

	@Override
	public void setId(Long id) {
		this.tareaProcesamientoId = id;
	}

	public void setServiceBusquedaDobles(
			ServiceBusquedaDobles serviceBusquedaDobles) {
		this.serviceBusquedaDobles = serviceBusquedaDobles;
	}

	public ServiceBusquedaDobles getServiceBusquedaDobles() {
		return serviceBusquedaDobles;
	}

	public void setServiceCalculoPosicion(
			ServiceCalculoPosicion serviceCalculoPosicion) {
		this.serviceCalculoPosicion = serviceCalculoPosicion;
	}

	public ServiceCalculoPosicion getServiceCalculoPosicion() {
		return serviceCalculoPosicion;
	}

	public static Log getLog() {
		return LOG;
	}

}