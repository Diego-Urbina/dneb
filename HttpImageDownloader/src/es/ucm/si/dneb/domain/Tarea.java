
package es.ucm.si.dneb.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="TAREA")
@NamedQueries({
	@NamedQuery(name="TareasActualizadasFecha",query="select t from Tarea t where fechaUltimaActulizacion=?"),
	@NamedQuery(name="TareasCreadasFecha",query="select t from Tarea t where fechaCreacion=?"),
	@NamedQuery(name="TodasTareas",query="select t from Tarea t order by fechaCreacion")
})
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TAREA")
    private long idTarea;
    
    @Column(name="FECHACREACION", nullable =false)
    private Date fechaCreacion;
    
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
    
    @ManyToMany
    @JoinTable(name="TAREA_SURVEY_JT")
    @JoinColumn(name="SURVEY_FK")
    private List<Survey> surveys;
    
    @Column(name="SOLAPAMIENTO")
    private double solapamiento;
    
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


    
    
    
    

}
