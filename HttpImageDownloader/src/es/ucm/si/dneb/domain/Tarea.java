
package es.ucm.si.dneb.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name="TAREA")
@NamedQueries({
	//@NamedQuery(name="UltimaCreada",query="select from Tarea t where "),
	//@NamedQuery(name="UltimaActualizada",query="select from Tarea t where "),
	@NamedQuery(name="TodasTareas",query="select t from Tarea t order by fechaCreacion")
})
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TAREA")
    private long idTarea;

    private Date fechaCreacion;
    
    private Date fechaUltimaActualizacion;
    
    private String arInicial;
    
    private String decInicial;
    
    private String arFinal;
    
    private String decFinal;
    
    private double alto;
    
    private double ancho;
    
    @ManyToOne
    @JoinColumn(name="SURVEYINI_ID_FK",nullable=false)
    private Survey surveyInicial;
    
    @ManyToOne
    @JoinColumn(name="SURVEYFIN_ID_FK",nullable=false)
    private Survey surveyFinal;
    
    private double solapamiento;
    
    @ManyToOne
    @JoinColumn(name="FORSOLAPAMIENTO_ID_FK",nullable=false)
    private FormatoSolapamiento formatoSolapamiento;
    
    @ManyToOne
    @JoinColumn(name="FORFICHERO_ID_FK",nullable=false) 
    private FormatoFichero formatoFichero;
    
    @OneToMany(mappedBy="tarea")
    private Collection<Descarga> descargas;

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

	public void setDescargas(Collection<Descarga> descargas) {
		this.descargas = descargas;
	}

	public Collection<Descarga> getDescargas() {
		return descargas;
	}

	public void setSurveyInicial(Survey surveyInicial) {
		this.surveyInicial = surveyInicial;
	}

	public Survey getSurveyInicial() {
		return surveyInicial;
	}

	public void setSurveyFinal(Survey surveyFinal) {
		this.surveyFinal = surveyFinal;
	}

	public Survey getSurveyFinal() {
		return surveyFinal;
	}

	public void setSolpamiento(double solpamiento) {
		this.solapamiento = solpamiento;
	}

	public double getSolpamiento() {
		return solapamiento;
	}

	public void setFormatoSolapamiento(FormatoSolapamiento formatoSolapamiento) {
		this.formatoSolapamiento = formatoSolapamiento;
	}

	public FormatoSolapamiento getFormatoSolapamiento() {
		return formatoSolapamiento;
	}

	public void setFormatoFichero(FormatoFichero formatoFichero) {
		this.formatoFichero = formatoFichero;
	}

	public FormatoFichero getFormatoFichero() {
		return formatoFichero;
	}
    
    
    
    
    

}
