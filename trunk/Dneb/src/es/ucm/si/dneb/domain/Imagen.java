package es.ucm.si.dneb.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@Entity
@NamedQueries({
	@NamedQuery(name="Imagen:dameNumeroDescargasDeUnaTarea",query="select count(*) from Imagen d where tarea=?"),
	@NamedQuery(name="Imagen:dameImagenesDeUnaTarea",query="select d from Imagen d where tarea=?"),
	@NamedQuery(name="Imagen:dameNumeroDescargasPendientesDeUnaTarea",query="select count(*) from Imagen d where tarea=? and descargada=false")
})
@Table(name="IMAGEN")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idDescarga",
    "ascensionRecta",
    "declinacion"
})
@XmlRootElement(name = "Imagen")
public class Imagen {
	
	public Imagen(){
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_DESCARGA")
    @XmlElement(required = true)
    private long idDescarga;
	
	@Column(name="AR", nullable =false)
	@XmlElement(required = true)
	private String ascensionRecta;
	
	@Column(name="DECLI", nullable =false)
	@XmlElement(required = true)
	private String declinacion;
	
	@ManyToOne
	@JoinColumn(name="Survey_ID_FK",nullable=false)
	@XmlTransient
	private Survey survey;
	
	@Column(name="DESCARGADA", nullable =false)
	@XmlTransient
	private boolean descargada;
	
	@Column(name="FECHA_DESCARGA")
	@XmlTransient
	private Date fechaDescarga;
	
	@Column(name="RUTA")
	@XmlTransient
	private String rutaFichero;
	
	@Column(name="ANCHO")
	@XmlTransient
	private Double ancho;

	@ManyToOne
    @JoinColumn(name="TAREA_ID_FK",nullable=false)
    @XmlTransient
	private Tarea tarea;
	
	@OneToMany(mappedBy="imagen")
	@XmlTransient
    private List<ProcImagen> procImagen;
	

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

	public void setProcesamientoImagen(List<ProcImagen> procImagen) {
		this.procImagen = procImagen;
	}

	public List<ProcImagen> getProcesamientoImagen() {
		return procImagen;
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
	    
	    retValue = "Imagen ( "
	        + super.toString() + TAB
	        + "idDescarga = " + this.idDescarga + TAB
	        + "ascensionRecta = " + this.ascensionRecta + TAB
	        + "declinacion = " + this.declinacion + TAB
	        + "survey = " + this.survey + TAB
	        + "descargada = " + this.descargada + TAB
	        + "fechaDescarga = " + this.fechaDescarga + TAB
	        + "rutaFichero = " + this.rutaFichero + TAB
	        + "ancho = " + this.ancho + TAB
	        
	        
	        + " )";
	
	    return retValue;
	}


	
	
}
