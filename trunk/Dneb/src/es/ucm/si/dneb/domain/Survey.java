package es.ucm.si.dneb.domain;

import java.util.Collection;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Survey:dameSurveyPorDescripcion",query="select s from Survey s where descripcion=?"),
	@NamedQuery(name="Survey:dameTodosLosSurveys",query="select s from Survey s")
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

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "Survey ( "
	        + super.toString() + TAB
	        + "idSurvey = " + this.idSurvey + TAB
	        + "descripcion = " + this.descripcion + TAB
	        + "tareas = " + this.tareas + TAB
	        + " )";
	
	    return retValue;
	}

}
