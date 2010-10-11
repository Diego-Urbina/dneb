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
import javax.persistence.Table;

@Entity
@Table(name="TIP_PROC")
@NamedQueries({
	@NamedQuery(name="TipoProcesamiento:dameTiposProcesamiento",query="select t.alias from TipoProcesamiento t"),
	@NamedQuery(name="TipoProcesamiento:dameTipoPorAlias",query="select t from TipoProcesamiento t where t.alias=?")
})
public class TipoProcesamiento {
	
	
	
	@Id
    @Column(name="ID_TIP_PROC")
    private long idTipoProcesamiento;
	
	@Column(name="ALIAS",nullable=false,unique=true)
	private String alias;
	
	@Column(name="DESCRIPCION",unique=true)
	private String descripcion;
	
	@OneToMany(mappedBy="tipoProcesamiento")
	private Collection<ProcTarea> procTareas;

	public long getIdTipoProcesamiento() {
		return idTipoProcesamiento;
	}

	public void setIdTipoProcesamiento(long idTipoProcesamiento) {
		this.idTipoProcesamiento = idTipoProcesamiento;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<ProcTarea> getTareaProcesamientos() {
		return procTareas;
	}

	public void setTareaProcesamientos(
			Collection<ProcTarea> procTareas) {
		this.procTareas = procTareas;
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
	    
	    retValue = "TipoProcesamiento ( "
	        + super.toString() + TAB
	        + "idTipoProcesamiento = " + this.idTipoProcesamiento + TAB
	        + "alias = " + this.alias + TAB
	        + "descripcion = " + this.descripcion + TAB
	        + "procTareas = " + this.procTareas + TAB
	        + " )";
	
	    return retValue;
	}
	
	

	
	
	
	

}
