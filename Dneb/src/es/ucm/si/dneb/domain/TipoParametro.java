package es.ucm.si.dneb.domain;

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
@Table(name="TIPO_PARAMETRO")
public class TipoParametro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TIPO_PARAMETRO")
    private long idTipoParametro;
	
	@Column(name="ALIAS",nullable=false,unique=true)
	private String alias;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@OneToMany(mappedBy="tipoParametro")
	private List<Parametro> parametros;
	
	@ManyToOne
	@JoinColumn(name="TIPO_PROC_ID_FK",nullable=false) 
	private TipoProcesamiento tipoProcesamiento;

	
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

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setTipoProcesamiento(TipoProcesamiento tipoProcesamiento) {
		this.tipoProcesamiento = tipoProcesamiento;
	}

	public TipoProcesamiento getTipoProcesamiento() {
		return tipoProcesamiento;
	}
	
	
	
}
