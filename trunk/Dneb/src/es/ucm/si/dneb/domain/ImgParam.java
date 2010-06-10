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
public class ImgParam {

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
	private ParamType tipoParametro;
	
	@ManyToOne
	@JoinColumn(name="IMG",nullable=false) 
	private ImageProsec procImagen;
	

	

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

	public ParamType getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(ParamType tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public ImageProsec getProcImagen() {
		return procImagen;
	}

	public void setProcImagen(ImageProsec procImagen) {
		this.procImagen = procImagen;
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
	    
	    retValue = "ImgParam ( "
	        + super.toString() + TAB
	        + "idParametroImagen = " + this.idParametroImagen + TAB
	        + "valorNum = " + this.valorNum + TAB
	        + "valorAlfa = " + this.valorAlfa + TAB
	        + "tipoParametro = " + this.tipoParametro + TAB
	       
	        + " )";
	
	    return retValue;
	}
	
	


	
	

}
	
