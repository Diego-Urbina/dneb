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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="ProcTarea.getNumeroProcesamientoCompletados",query="select count(*) from ProcImagen p where p.procTarea=? and p.finalizada=true"),
	@NamedQuery(name="ProcTarea.getProcesamientosByType",query="select t from ProcTarea t where t.tipoProcesamiento=?"),
	@NamedQuery(name="ProcTarea.getImagenesGemelas",query="select p from  ProcImagen p where procTarea=? and imagen.ascensionRecta=? and imagen.declinacion=?"),
	@NamedQuery(name="ProcTarea.getTareaProcesamientoActivo",query="select t from ProcTarea t where t.activa=true"),
	@NamedQuery(name="ProcTarea.getAllProcesamientoImagen",query="select p from ProcImagen p where p.procTarea=? "),
	@NamedQuery(name="ProcTarea.getAllProcesamientos",query="select t from ProcTarea t"),
	@NamedQuery(name="ProcTarea.getParams",query="select t.paramProcTareas from ProcTarea t where t=?"),
	@NamedQuery(name="ProcTarea.getNumeroProcesamientos",query="select count(*) from ProcImagen p where p.procTarea=?")
	
})
@Table(name="TAREA_PROCESAMIENTO")
public class ProcTarea {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long idProcesamiento;
	
	@Column(name="ALIAS")
	private String alias;
	
	@Column(name="DESCRIPCION")
	private String description;
	
    @Column(name="FECHACREACION", nullable =false)
    private Date fechaCreacion;
    
    @Column(name="FINALIZADA", nullable =false)
    private boolean finalizada;
    
    @Column(name="ACTIVA", nullable =false)
    private boolean activa;
    
    @Column(name="ULTACTUALIZACION")
    private Date fechaUltimaAct;
	
	@ManyToOne
	@JoinColumn(name="TAREA",nullable=false)
	private Tarea tarea;
	
	@OneToMany(mappedBy="procTarea")
	private List<ProcImagen> procesamientoImagenes;

	@ManyToOne
	@JoinColumn(name="TIPO_PROC",nullable=false) 
	private TipoProcesamiento tipoProcesamiento;
	
	@OneToMany(mappedBy="procTarea")
	private List<ParamProcTarea> paramProcTareas;


	public String getAlias() {
		return alias;
	}

	public void setAlias(final String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(final Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(final boolean finalizada) {
		this.finalizada = finalizada;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(final boolean activa) {
		this.activa = activa;
	}

	



	public Date getFechaUltimaAct() {
		return fechaUltimaAct;
	}

	public void setFechaUltimaAct(Date fechaUltimaAct) {
		this.fechaUltimaAct = fechaUltimaAct;
	}

	
	public void setTipoProcesamiento(TipoProcesamiento tipoProcesamiento) {
		this.tipoProcesamiento = tipoProcesamiento;
	}

	public TipoProcesamiento getTipoProcesamiento() {
		return tipoProcesamiento;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public void setProcesamientoImagenes(List<ProcImagen> procesamientoImagenes) {
		this.procesamientoImagenes = procesamientoImagenes;
	}

	public List<ProcImagen> getProcesamientoImagenes() {
		return procesamientoImagenes;
	}

	public void setParametros(List<ParamProcTarea> paramProcTareas) {
		this.paramProcTareas = paramProcTareas;
	}

	public List<ParamProcTarea> getParametros() {
		return paramProcTareas;
	}

	public void setIdProcesamiento(long idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
	}

	public long getIdProcesamiento() {
		return idProcesamiento;
	}
	
	


	

	
	
	

}
