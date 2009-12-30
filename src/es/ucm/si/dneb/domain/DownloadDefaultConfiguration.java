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
	@NamedQuery(name="DownloadDefaultConfiguration:dameConfiguracionPorAlias",query="select c from DownloadDefaultConfiguration c where alias=?"),
	@NamedQuery(name="DownloadDefaultConfiguration:dameTodasConfiguraciones",query="select c from DownloadDefaultConfiguration c")
})
public class DownloadDefaultConfiguration {
	
	
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
	
	
	
	
	
	
	
	
	
}
