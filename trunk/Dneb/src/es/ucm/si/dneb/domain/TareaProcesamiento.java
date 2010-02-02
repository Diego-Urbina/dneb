package es.ucm.si.dneb.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAREA_PROCESAMIENTO")
public class TareaProcesamiento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long idTarea;
	
	private String alias;
	
	private String description;
	
    @Column(name="FECHACREACION", nullable =false)
    private Date fechaCreacion;
    
    @Column(name="FINALIZADA", nullable =false)
    private boolean finalizada;
    
    @Column(name="ACTIVA", nullable =false)
    private boolean activa;
    
    @Column(name="ULTACTUALIZACION")
    private Date fechaUltimaAct;
	
	@ManyToMany(mappedBy="tareasProcesamiento")
	private List<Tarea> tareas;

	

	public String getAlias() {
		return alias;
	}

	public void setAlias(final String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(final Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(final boolean finalizada) {
		this.finalizada = finalizada;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(final boolean activa) {
		this.activa = activa;
	}

	

	public long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(long idTarea) {
		this.idTarea = idTarea;
	}

	public Date getFechaUltimaAct() {
		return fechaUltimaAct;
	}

	public void setFechaUltimaAct(Date fechaUltimaAct) {
		this.fechaUltimaAct = fechaUltimaAct;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	
	
	

}
