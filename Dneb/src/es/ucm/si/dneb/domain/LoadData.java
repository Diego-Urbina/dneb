package es.ucm.si.dneb.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CARGA_DATOS")
@NamedQueries({
	@NamedQuery(name="LoadData:dameTodosPuntosRelevantes",query="select p from LoadData p"),
	@NamedQuery(name="LoadData:dameTodosPuntosRelevantesNoProcesados",query="select p from LoadData p where procesado=false")
})
public class LoadData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_PUNTO")
    private long idPunto;
	
	@Column(name="ASCENCIONRECTA", nullable =false)
	private double ascencionRecta;
	
	@Column(name="DECLINACION", nullable =false)
	private double declinacion;
	
	@Column(name="PROCESADO", nullable =false)
	private boolean procesado;
	
	@Column(name="DESCRIPCION", nullable =false, length=200)
	private String descripcion;
	
	
	@Column(name="ALTO")
	private Double alto;
	
	@Column(name="ANCHO")
	private Double ancho;
	
	@Column(name="PATH")
	private String path;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PUNTOSRELEVANTES_SURVEY_JT")
	private List<Survey> surveys;
	
	
	public long getIdPunto() {
		return idPunto;
	}

	public void setIdPunto(long idPunto) {
		this.idPunto = idPunto;
	}

	public double getAscencionRecta() {
		return ascencionRecta;
	}

	public void setAscencionRecta(double ascencionRecta) {
		this.ascencionRecta = ascencionRecta;
	}

	public double getDeclinacion() {
		return declinacion;
	}

	public void setDeclinacion(double declinacion) {
		this.declinacion = declinacion;
	}

	public boolean isProcesado() {
		return procesado;
	}

	public void setProcesado(boolean procesado) {
		this.procesado = procesado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getAlto() {
		return alto;
	}

	public void setAlto(Double alto) {
		this.alto = alto;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
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
	    
	    retValue = "LoadData ( "
	        + super.toString() + TAB
	        + "idPunto = " + this.idPunto + TAB
	        + "ascencionRecta = " + this.ascencionRecta + TAB
	        + "declinacion = " + this.declinacion + TAB
	        + "procesado = " + this.procesado + TAB
	        + "descripcion = " + this.descripcion + TAB
	        + "alto = " + this.alto + TAB
	        + "ancho = " + this.ancho + TAB
	        + "path = " + this.path + TAB
	        + "surveys = " + this.surveys + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
}
