package es.ucm.si.dneb.domain;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DownloadConfig:dameConfiguracionPorAlias",query="select c from DownloadConfig c where alias=?"),
	@NamedQuery(name="DownloadConfig:dameTodasConfiguraciones",query="select c from DownloadConfig c")
})
public class DownloadConfig {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_DOWN_DEF_CONF")
    private long id;
	
	@Column(unique=true,nullable=false)
	private String alias;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="DOWNCONFIG_SURVEY_JT")
	private List<Survey> surveys;
	
	@Column(name="ALTO")
	private Double alto;
	
	@Column(name="ANCHO")
	private Double ancho;
	
	@Column(name="PATH")
	private String path;
	
	@ManyToOne
	@JoinColumn(name="FORFICHERO_ID_FK",nullable=false) 
	private FormatoFichero formatoFichero;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
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

	public FormatoFichero getFormatoFichero() {
		return formatoFichero;
	}

	public void setFormatoFichero(FormatoFichero formatoFichero) {
		this.formatoFichero = formatoFichero;
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
	    
	    retValue = "DownloadConfig ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "alias = " + this.alias + TAB
	        + "surveys = " + this.surveys + TAB
	        + "alto = " + this.alto + TAB
	        + "ancho = " + this.ancho + TAB
	        + "path = " + this.path + TAB
	        + "formatoFichero = " + this.formatoFichero + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
	
	
	
	
	
	
}
