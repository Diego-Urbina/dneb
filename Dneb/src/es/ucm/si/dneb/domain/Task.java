
package es.ucm.si.dneb.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="TAREA")
@NamedQueries({
	@NamedQuery(name="Task:DameTareasActualizadasFecha",query="select t from Task t  where fechaUltimaActulizacion=?"),
	@NamedQuery(name="Task:DameTareasCreadasFecha",query="select t from Task t  where fechaCreacion=?"),
	@NamedQuery(name="Task:DameTodasTareasActivas",query="select t from Task t  where descargaActiva=true"),
	@NamedQuery(name="Task:DameTodasTareasPendientes",query="select t from Task t where descargaFinalizada=false"),
	@NamedQuery(name="Task:DameTodasTareasActualizadasAntesFecha",query="select t from Task t  where fechaUltimaActulizacion<=?"),
	@NamedQuery(name="Task:DameDescargasPendientesDeEstaTarea",query="select d from Task t JOIN t.imagens d where t.idTarea=? and d.descargada=false"),
	@NamedQuery(name="Task:DameTodasTareas",query="select t from Task t  order by fechaCreacion"),
	@NamedQuery(name="Task:DameTareasFinalizadas",query="select t from Task t where descargaFinalizada=true")
})
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Task(){
		
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TAREA")
    private long idTarea;
    
    @Column(name="ALIAS")
    private String alias;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @Column(name="FECHACREACION", nullable =false)
    private Date fechaCreacion;
    
    @Column(name="DESCARGA_FINALIZADA", nullable =false)
    private boolean descargaFinalizada;
    
    @Column(name="DESCARGA_ACTIVA", nullable =false)
    private boolean descargaActiva;
    
    @Column(name="FECHAULTACTUALIZACION")
    private Date fechaUltimaActualizacion;
    
    @Column(name="ARINICIAL", nullable =false)
    private String arInicial;
    
    @Column(name="DECINICIAL", nullable =false) 
    private String decInicial;
    
    @Column(name="ARFINAL", nullable =false)
    private String arFinal;
    
    @Column(name="DECFINAL", nullable =false)
    private String decFinal;
    
    @Column(name="ALTO", nullable =false)
    private double alto;
    
    @Column(name="ANCHO", nullable =false)
    private double ancho;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="TAREA_SURVEY_JT")
    private List<Survey> surveys;
    
    @OneToMany(mappedBy="tarea")
    private List<TaskProsec> tareasProcesamiento;
    
    @Column(name="SOLAPAMIENTO")
    private double solapamiento;
    
    @Column(name="ruta")
    private String ruta;
    
    @ManyToOne
    @JoinColumn(name="FORFICHERO_ID_FK",nullable=false) 
    private FileFormat formatoFichero;
    
    @OneToMany(mappedBy="tarea")
    private List<Image> imagens;

	public long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(long idTarea) {
		this.idTarea = idTarea;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public String getArInicial() {
		return arInicial;
	}

	public void setArInicial(String arInicial) {
		this.arInicial = arInicial;
	}

	public String getDecInicial() {
		return decInicial;
	}

	public void setDecInicial(String decInicial) {
		this.decInicial = decInicial;
	}

	public String getArFinal() {
		return arFinal;
	}

	public void setArFinal(String arFinal) {
		this.arFinal = arFinal;
	}

	public String getDecFinal() {
		return decFinal;
	}

	public void setDecFinal(String decFinal) {
		this.decFinal = decFinal;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDescargas(List<Image> imagens) {
		this.imagens = imagens;
	}

	public List<Image> getDescargas() {
		return imagens;
	}



	public void setSolpamiento(double solpamiento) {
		this.solapamiento = solpamiento;
	}

	public double getSolpamiento() {
		return solapamiento;
	}

	public void setFormatoFichero(FileFormat formatoFichero) {
		this.formatoFichero = formatoFichero;
	}

	public FileFormat getFormatoFichero() {
		return formatoFichero;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getRuta() {
		return ruta;
	}

	public void setFinalizada(boolean finalizada) {
		this.descargaFinalizada = finalizada;
	}

	public boolean isFinalizada() {
		return descargaFinalizada;
	}

	public void setActiva(boolean activa) {
		this.descargaActiva = activa;
	}

	public boolean isActiva() {
		return descargaActiva;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTareasProcesamiento(List<TaskProsec> tareasProcesamiento) {
		this.tareasProcesamiento = tareasProcesamiento;
	}

	public List<TaskProsec> getTareasProcesamiento() {
		return tareasProcesamiento;
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
	    
	    retValue = "Task ( "
	        + super.toString() + TAB
	        + "idTarea = " + this.idTarea + TAB
	        + "alias = " + this.alias + TAB
	        + "descripcion = " + this.descripcion + TAB
	        + "fechaCreacion = " + this.fechaCreacion + TAB
	        + "descargaFinalizada = " + this.descargaFinalizada + TAB
	        + "descargaActiva = " + this.descargaActiva + TAB
	        + "fechaUltimaActualizacion = " + this.fechaUltimaActualizacion + TAB
	        + "arInicial = " + this.arInicial + TAB
	        + "decInicial = " + this.decInicial + TAB
	        + "arFinal = " + this.arFinal + TAB
	        + "decFinal = " + this.decFinal + TAB
	        + "alto = " + this.alto + TAB
	        + "ancho = " + this.ancho + TAB
	        + "surveys = " + this.surveys + TAB
	        + "tareasProcesamiento = " + this.tareasProcesamiento + TAB
	        + "solapamiento = " + this.solapamiento + TAB
	        + "ruta = " + this.ruta + TAB
	        + "formatoFichero = " + this.formatoFichero + TAB
	        + "imagens = " + this.imagens + TAB
	        + " )";
	
	    return retValue;
	}



    
    
    
    

}
