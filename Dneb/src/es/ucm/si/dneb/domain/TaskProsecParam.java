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
@Table(name="PARAMETRO_TAREA_PROC")
public class TaskProsecParam {
	
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
	private ParamType tipoParametro;
	
	@ManyToOne
	@JoinColumn(name="PROC",nullable=false) 
	private TaskProsec procTarea;

	
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

	public ParamType getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(ParamType tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public void setTareaProcesamiento(TaskProsec procTarea) {
		this.procTarea = procTarea;
	}

	public TaskProsec getTareaProcesamiento() {
		return procTarea;
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
	    
	    retValue = "TaskProsecParam ( "
	        + super.toString() + TAB
	        + "idParametro = " + this.idParametro + TAB
	        + "valorNum = " + this.valorNum + TAB
	        + "valorAlfa = " + this.valorAlfa + TAB
	        + "tipoParametro = " + this.tipoParametro + TAB
	        + "procTarea = " + this.procTarea + TAB
	        + " )";
	
	    return retValue;
	}
	
	

}
