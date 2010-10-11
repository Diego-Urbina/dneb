package es.ucm.si.dneb.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_PARAMETRO")
public class TipoParametro {
	
	@Id
    @Column(name="ID_TIPO_PARAMETRO")
    private long idTipoParametro;
	
	@Column(name="ALIAS",nullable=false,unique=true)
	private String alias;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@OneToMany(mappedBy="tipoParametro")
	private List<ParamProcTarea> paramProcTareas;
	
	@OneToMany(mappedBy="tipoParametro")
	private List<ParamImg> paramImgs;

	
	public long getIdTipoParametro() {
		return idTipoParametro;
	}

	public void setIdTipoParametro(long idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
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

	public void setParametros(List<ParamProcTarea> paramProcTareas) {
		this.paramProcTareas = paramProcTareas;
	}

	public List<ParamProcTarea> getParametros() {
		return paramProcTareas;
	}

	public void setParamImgs(List<ParamImg> paramImgs) {
		this.paramImgs = paramImgs;
	}

	public List<ParamImg> getParamImgs() {
		return paramImgs;
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
	    
	    retValue = "TipoParametro ( "
	        + super.toString() + TAB
	        + "idTipoParametro = " + this.idTipoParametro + TAB
	        + "alias = " + this.alias + TAB
	        + "descripcion = " + this.descripcion + TAB
	      
	        + " )";
	
	    return retValue;
	}

	
	
	
}
