package es.ucm.si.dneb.domain;

import java.util.Date;

import javax.persistence.*;


@Entity
@NamedQueries({
	@NamedQuery(name="Imagen:dameNumeroDescargasDeUnaTarea",query="select count(*) from Imagen d where tarea=?"),
	@NamedQuery(name="Imagen:dameNumeroDescargasPendientesDeUnaTarea",query="select count(*) from Imagen d where tarea=? and descargada=false")
})
@Table(name="IMAGEN")
public class Imagen {
	
	public Imagen(){
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_DESCARGA")
    private long idDescarga;
	
	@Column(name="AR", nullable =false)
	private String ascensionRecta;
	
	@Column(name="DECLI", nullable =false)
	private String declinacion;
	
	@ManyToOne
	@JoinColumn(name="Survey_ID_FK",nullable=false)
	private Survey survey;
	
	@Column(name="DESCARGADA", nullable =false)
	private boolean descargada;
	
	@Column(name="FECHA_DESCARGA")
	private Date fechaDescarga;
	
	 @Column(name="RUTA")
	private String rutaFichero;
	
	 @Column(name="ANCHO")
	private Double ancho;

	@ManyToOne
    @JoinColumn(name="TAREA_ID_FK",nullable=false)
	private Tarea tarea;

	public void setIdDescarga(long idDescarga) {
		this.idDescarga = idDescarga;
	}

	public long getIdDescarga() {
		return idDescarga;
	}

	public void setAscensionRecta(String ascensionRecta) {
		this.ascensionRecta = ascensionRecta;
	}

	public String getAscensionRecta() {
		return ascensionRecta;
	}

	public void setDeclinacion(String declinacion) {
		this.declinacion = declinacion;
	}

	public String getDeclinacion() {
		return declinacion;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Tarea getTarea() {
		return tarea;
	}



	public void setDescargada(boolean finalizada) {
		this.descargada = finalizada;
	}

	public boolean isDescargada() {
		return descargada;
	}

	public void setFechaDescarga(Date fechaFinalizacion) {
		this.fechaDescarga = fechaFinalizacion;
	}

	public Date getFechaDescarga() {
		return fechaDescarga;
	}

	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getAncho() {
		return ancho;
	}


	
	
}
