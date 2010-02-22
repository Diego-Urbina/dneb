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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TIP_PROC")
    private long idTipoProcesamiento;
	
	@Column(name="ALIAS",nullable=false,unique=true)
	private String alias;
	
	@Column(name="DESCRIPCION",unique=true)
	private String descripcion;
	
	@OneToMany(mappedBy="tipoProcesamiento")
	private Collection<TareaProcesamiento> tareaProcesamientos;

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

	public Collection<TareaProcesamiento> getTareaProcesamientos() {
		return tareaProcesamientos;
	}

	public void setTareaProcesamientos(
			Collection<TareaProcesamiento> tareaProcesamientos) {
		this.tareaProcesamientos = tareaProcesamientos;
	}
	
	

	
	
	
	

}