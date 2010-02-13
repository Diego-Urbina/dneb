package es.ucm.si.dneb.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROCESAMIENTO_IMAGEN")
public class ProcesamientoImagen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	private String alias;

	private String description;

	@Column(name = "FINALIZADA", nullable = false)
	private boolean finalizada;

	@Column(name = "FECHA_PROCESAMIENTO")
	private Date fechaProcesamiento;

	@ManyToOne
	@JoinColumn(name="IMG_ID_FK",nullable=false)
	private Imagen imagen;
	
	@ManyToOne
    @JoinColumn(name="PROC_ID_FK",nullable=false)
	private TareaProcesamiento tareaProcesamiento;
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}

	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

	

	

	public void setTareaProcesamiento(TareaProcesamiento tareaProcesamiento) {
		this.tareaProcesamiento = tareaProcesamiento;
	}

	public TareaProcesamiento getTareaProcesamiento() {
		return tareaProcesamiento;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public Imagen getImagen() {
		return imagen;
	}
	
	

}
