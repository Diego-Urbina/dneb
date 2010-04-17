package es.ucm.si.dneb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INFO_REL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "description",
    "fecha",
    "tipoInformacionRelevante",
    "imagenes"
})
@XmlRootElement(name = "InformacionRelevante")
public class InformacionRelevante {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    @XmlElement(required = true)
    private long id;
	
	@Column(name="DESCRIPCION" , length=5000 )
	@XmlElement(required = true)
	private String description;
	
	@Column(name="FECHA",nullable =false)
	@XmlElement(required = true)
	private Date fecha;

	@ManyToOne
    @JoinColumn(name="TIP_INF_REL_FK",nullable=false)
    @XmlElement(required = true)
	private TipoInformacionRelevante tipoInformacionRelevante;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="INFOREL_IMAGEN_JT")
	@XmlElement(required = true)
	private List<Imagen> imagenes;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setTipoInformacionRelevante(TipoInformacionRelevante tipoInformacionRelevante) {
		this.tipoInformacionRelevante = tipoInformacionRelevante;
	}

	public TipoInformacionRelevante getTipoInformacionRelevante() {
		return tipoInformacionRelevante;
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
	    
	    retValue = "InformacionRelevante ( "
	       +"id = " + this.id + TAB
	        + "description = " + this.description + TAB
	        + "fecha = " + this.fecha + TAB
	        + "tipoInformacionRelevante = " + this.tipoInformacionRelevante + TAB
	        + "imagenes = " + this.imagenes + TAB
	        + " )";
	
	    return retValue;
	}


	
	

}
