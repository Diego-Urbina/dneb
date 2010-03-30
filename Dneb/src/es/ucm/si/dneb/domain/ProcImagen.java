package es.ucm.si.dneb.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROCESAMIENTO_IMAGEN")
public class ProcImagen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	private String alias;

	private String description;

	@Column(name = "FINALIZADA", nullable = false)
	private boolean finalizada;

	@Column(name = "FECHA_PROCESAMIENTO")
	private Date fechaProcesamiento;

	@ManyToOne
	@JoinColumn(name="IMG_ID_FK",nullable=false)
	private Imagen imagen;
	
	@ManyToOne
    @JoinColumn(name="PROC_ID_FK",nullable=false)
	private ProcTarea procTarea;
	
	@OneToMany(mappedBy="procImagen")
	private List<ParamImg> params;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}

	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

	

	

	public void setTareaProcesamiento(ProcTarea procTarea) {
		this.procTarea = procTarea;
	}

	public ProcTarea getTareaProcesamiento() {
		return procTarea;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setParams(List<ParamImg> params) {
		this.params = params;
	}

	public List<ParamImg> getParams() {
		return params;
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
	    
	    retValue = "ProcImagen ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "alias = " + this.alias + TAB
	        + "description = " + this.description + TAB
	        + "finalizada = " + this.finalizada + TAB
	        + "fechaProcesamiento = " + this.fechaProcesamiento + TAB
	        + "imagen = " + this.imagen + TAB
	        + "procTarea = " + this.procTarea + TAB
	        + "params = " + this.params + TAB
	        + " )";
	
	    return retValue;
	}
	
	

}
