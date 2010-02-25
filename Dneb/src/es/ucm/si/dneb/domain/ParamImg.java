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
@Table(name="PARAM_IMG")
public class ParamImg {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_PARAM_IMG")
    private long idParametroImagen;
	
	@Column(name="VAL_NUM")
	private Double valorNum;
	
	@Column(name="VAL_ALFA")
	private String valorAlfa;
	
	@ManyToOne
	@JoinColumn(name="TIP_PARAM",nullable=false) 
	private TipoParametro tipoParametro;
	
	@ManyToOne
	@JoinColumn(name="IMG",nullable=false) 
	private ProcImagen procImagen;
	

	

	public long getIdParametroImagen() {
		return idParametroImagen;
	}

	public void setIdParametroImagen(long idParametroImagen) {
		this.idParametroImagen = idParametroImagen;
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

	public ProcImagen getProcImagen() {
		return procImagen;
	}

	public void setProcImagen(ProcImagen procImagen) {
		this.procImagen = procImagen;
	}
	
	


	
	

}
	
