package es.ucm.si.dneb.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="FormatoFichero:dameFormatoPorDescripcion",query="select s from Survey s where descripcion=?")
})
public class FormatoFichero {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_FORMATO_FICHERO")
    private long idFormatoFichero;
	
	private String descipcion;
	
	
	@OneToMany(mappedBy="formatoFichero")
	private Collection<Tarea> tareas;
	
	
	
	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setIdFormatoFichero(long idFormatoFichero) {
		this.idFormatoFichero = idFormatoFichero;
	}

	public long getIdFormatoFichero() {
		return idFormatoFichero;
	}

	public void setTareas(Collection<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Collection<Tarea> getTareas() {
		return tareas;
	}
	
	
	
}
