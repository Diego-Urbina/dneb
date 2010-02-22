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
	@NamedQuery(name="TareaProcesamiento.getNumeroProcesamientoCompletados",query="select count(*) from ProcesamientoImagen p where p.tareaProcesamiento=? and p.finalizada=true"),
	@NamedQuery(name="TareaProcesamiento.getProcesamientosByType",query="select t from TareaProcesamiento t where t.tipoProcesamiento=?"),
	@NamedQuery(name="TareaProcesamiento.getImagenesGemelas",query="select p from  ProcesamientoImagen p where tareaProcesamiento=? and imagen.ascensionRecta=? and imagen.declinacion=?"),
	@NamedQuery(name="TareaProcesamiento.getTareaProcesamientoActivo",query="select t from TareaProcesamiento t where t.activa=true"),
	@NamedQuery(name="TareaProcesamiento.getAllProcesamientoImagen",query="select p from ProcesamientoImagen p where p.tareaProcesamiento=? "),
	@NamedQuery(name="TareaProcesamiento.getAllProcesamientos",query="select t from TareaProcesamiento t"),
	@NamedQuery(name="TareaProcesamiento.getNumeroProcesamientos",query="select count(*) from ProcesamientoImagen p where p.tareaProcesamiento=?")
	
})
@Table(name="TAREA_PROCESAMIENTO")
public class TareaProcesamiento {
	
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
	
	@OneToMany(mappedBy="tareaProcesamiento")
	private List<ProcesamientoImagen> procesamientoImagenes;

	@ManyToOne
	@JoinColumn(name="TIPO_PROC",nullable=false) 
	private TipoProcesamiento tipoProcesamiento;
	
	@OneToMany(mappedBy="tareaProcesamiento")
	private List<Parametro> parametros;


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

	public void setProcesamientoImagenes(List<ProcesamientoImagen> procesamientoImagenes) {
		this.procesamientoImagenes = procesamientoImagenes;
	}

	public List<ProcesamientoImagen> getProcesamientoImagenes() {
		return procesamientoImagenes;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setIdProcesamiento(long idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
	}

	public long getIdProcesamiento() {
		return idProcesamiento;
	}
	
	


	

	
	
	

}
