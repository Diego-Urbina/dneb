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
	@NamedQuery(name="FileFormat:dameFormatoPorDescripcion",query="select f from FileFormat f where description=?"),
	@NamedQuery(name="FileFormat:dameTodosFormatos",query="select f from FileFormat f")
})
public class FileFormat {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_FORMATO_FICHERO")
    private long idFormatoFichero;
	
	private String alias;
	private String description;
	
	
	@OneToMany(mappedBy="formatoFichero")
	private Collection<Task> tareas;
	
	
	
	public void setAlias(String descipcion) {
		this.alias = descipcion;
	}

	public String getAlias() {
		return alias;
	}

	public void setIdFormatoFichero(long idFormatoFichero) {
		this.idFormatoFichero = idFormatoFichero;
	}

	public long getIdFormatoFichero() {
		return idFormatoFichero;
	}

	public void setTareas(Collection<Task> tareas) {
		this.tareas = tareas;
	}

	public Collection<Task> getTareas() {
		return tareas;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
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
	    
	    retValue = "FileFormat ( "
	        + super.toString() + TAB
	        + "idFormatoFichero = " + this.idFormatoFichero + TAB
	        + "alias = " + this.alias + TAB
	        + "description = " + this.description + TAB
	        + "tareas = " + this.tareas + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
}
