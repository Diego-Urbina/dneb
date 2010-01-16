
package es.ucm.si.dneb.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="TAREA")
@NamedQueries({
	@NamedQuery(name="Tarea:DameTareasActualizadasFecha",query="select t from Tarea t  where fechaUltimaActulizacion=?"),
	@NamedQuery(name="Tarea:DameTareasCreadasFecha",query="select t from Tarea t  where fechaCreacion=?"),
	@NamedQuery(name="Tarea:DameTodasTareasActivas",query="select t from Tarea t  where activa=true"),
	@NamedQuery(name="Tarea:DameTodasTareasPendientes",query="select t from Tarea t where finalizada=false"),
	@NamedQuery(name="Tarea:DameTodasTareasActualizadasAntesFecha",query="select t from Tarea t  where fechaUltimaActulizacion<=?"),
	@NamedQuery(name="Tarea:DameDescargasPendientesDeEstaTarea",query="select d from Tarea t JOIN t.imagens d where t.idTarea=? and d.finalizada=false"),
	@NamedQuery(name="Tarea:DameTodasTareas",query="select t from Tarea t  order by fechaCreacion")
})
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Tarea(){
		
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
    
    @Column(name="FINALIZADA", nullable =false)
    private boolean descargaFinalizada;
    
     
    
    @Column(name="ACTIVA", nullable =false)
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
    
    @Column(name="SOLAPAMIENTO")
    private double solapamiento;
    
    @Column(name="ruta")
    private String ruta;
    
    @ManyToOne
    @JoinColumn(name="FORFICHERO_ID_FK",nullable=false) 
    private FormatoFichero formatoFichero;
    
    @OneToMany(mappedBy="tarea")
    private List<Imagen> imagens;

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

	public void setDescargas(List<Imagen> imagens) {
		this.imagens = imagens;
	}

	public List<Imagen> getDescargas() {
		return imagens;
	}



	public void setSolpamiento(double solpamiento) {
		this.solapamiento = solpamiento;
	}

	public double getSolpamiento() {
		return solapamiento;
	}

	public void setFormatoFichero(FormatoFichero formatoFichero) {
		this.formatoFichero = formatoFichero;
	}

	public FormatoFichero getFormatoFichero() {
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



    
    
    
    

}
