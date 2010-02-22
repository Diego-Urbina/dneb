package es.ucm.si.dneb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PARAMETRO")
public class Parametro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_PARAMETRO")
    private long idParametro;
	
	@Column(name="VAL_NUM")
	private Double valorNum;
	
	@Column(name="VAL_ALFA")
	private String valorAlfa;
	
	@ManyToOne
	@JoinColumn(name="TIP_PARAM",nullable=false) 
	private TipoParametro tipoParametro;
	
	@ManyToOne
	@JoinColumn(name="PROC",nullable=false) 
	private TareaProcesamiento tareaProcesamiento;

	
	public long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	public Double getValorNum() {
		return valorNum;
	}

	public void setValorNum(Double valorNum) {
		this.valorNum = valorNum;
	}

	public String getValorAlfa() {
		return valorAlfa;
	}

	public void setValorAlfa(String valorAlfa) {
		this.valorAlfa = valorAlfa;
	}

	public TipoParametro getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametro tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public void setTareaProcesamiento(TareaProcesamiento tareaProcesamiento) {
		this.tareaProcesamiento = tareaProcesamiento;
	}

	public TareaProcesamiento getTareaProcesamiento() {
		return tareaProcesamiento;
	}
	
	

}
