package es.ucm.si.dneb.domain;

import java.util.Collection;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Survey:dameSurveyPorDescripcion",query="select s from Survey s where descripcion=?")
})
public class Survey {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_SURVEY")
    private long idSurvey;
	
	@Column(name="DESCRIPCION",nullable=false,unique=true)
	private String descripcion;
	
	@ManyToMany(mappedBy="surveys")
	private Collection<Tarea> tareas;

	public void setIdSurvey(long idSurvey) {
		this.idSurvey = idSurvey;
	}

	public long getIdSurvey() {
		return idSurvey;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTareas(Collection<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Collection<Tarea> getTareas() {
		return tareas;
	}

}
