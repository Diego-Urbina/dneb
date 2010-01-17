package es.ucm.si.dneb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="DOUBLE_STAR_CATALOG")
public class DoubleStarCatalog {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

	@Column(name="COORD")
	private String coordinates;
	
	@Column(name="DISC_AND_NUM")
	private String discovererAndNumber;
	
	@Column(name="COMPONENTS")
	private String components;
	
	@Column(name="FIST_OBS")
	private Date firstObservation;
	
	@Column(name="LAS_OBS")
	private Date lastObservation;
	
	@Column(name="NUM_OBS")
	private Integer numObservations;
	
	@Column(name="FIRST_POS_ANG")
	private Double firstPosAngle;
	
	@Column(name="LAST_POS_ANG")
	private Double lastPosAnges;
	
	@Column(name="FIRST_SEP")
	private Double firstSeparation;
	
	@Column(name="LAST_SEP")
	private Double lastSeparation;
	
	@Column(name="FIRST_STAR_MAGNITUDE")
	private Double firstStarMagnitude;
	
	@Column(name="SECOND_STAR_MAGNITUDE")
	private Double secondStarMagnitude;
	
	@Column(name="SPEC_TYPE")
	private String spectralType;
	
	@Column(name="PRIM_PROP_MOT_RA")
	private Double primaryProperMotionRa;
	
	@Column(name="PRIM_PROP_MOT_DEC")
	private Double primaryProperMotionDec;
	
	@Column(name="SEC_PROP_MOT_RA")
	private Double secondaryProperMotionRa;
	
	@Column(name="SEC_PROP_MOT_DEC")
	private Double secondaryProperMotionDec;
	
	@Column(name="DURCH_NUM")
	private String durchmusterungNumber;
	
	@Column(name="NOTES")
	private String notes;
	
	@Column(name="ARCH_COORD_2000")
	private String ArcsecondCoordinates2000;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getDiscovererAndNumber() {
		return discovererAndNumber;
	}

	public void setDiscovererAndNumber(String discovererAndNumber) {
		this.discovererAndNumber = discovererAndNumber;
	}

	public String getComponents() {
		return components;
	}

	public void setComponents(String components) {
		this.components = components;
	}

	public Date getFirstObservation() {
		return firstObservation;
	}

	public void setFirstObservation(Date firstObservation) {
		this.firstObservation = firstObservation;
	}

	public Date getLastObservation() {
		return lastObservation;
	}

	public void setLastObservation(Date lastObservation) {
		this.lastObservation = lastObservation;
	}

	public Integer getNumObservations() {
		return numObservations;
	}

	public void setNumObservations(Integer numObservations) {
		this.numObservations = numObservations;
	}

	public Double getFirstPosAngle() {
		return firstPosAngle;
	}

	public void setFirstPosAngle(Double firstPosAngle) {
		this.firstPosAngle = firstPosAngle;
	}

	public Double getLastPosAnges() {
		return lastPosAnges;
	}

	public void setLastPosAnges(Double lastPosAnges) {
		this.lastPosAnges = lastPosAnges;
	}

	public Double getFirstSeparation() {
		return firstSeparation;
	}

	public void setFirstSeparation(Double firstSeparation) {
		this.firstSeparation = firstSeparation;
	}

	public Double getLastSeparation() {
		return lastSeparation;
	}

	public void setLastSeparation(Double lastSeparation) {
		this.lastSeparation = lastSeparation;
	}

	public Double getFirstStarMagnitude() {
		return firstStarMagnitude;
	}

	public void setFirstStarMagnitude(Double firstStarMagnitude) {
		this.firstStarMagnitude = firstStarMagnitude;
	}

	public Double getSecondStarMagnitude() {
		return secondStarMagnitude;
	}

	public void setSecondStarMagnitude(Double secondStarMagnitude) {
		this.secondStarMagnitude = secondStarMagnitude;
	}

	public String getSpectralType() {
		return spectralType;
	}

	public void setSpectralType(String spectralType) {
		this.spectralType = spectralType;
	}

	public Double getPrimaryProperMotionRa() {
		return primaryProperMotionRa;
	}

	public void setPrimaryProperMotionRa(Double primaryProperMotionRa) {
		this.primaryProperMotionRa = primaryProperMotionRa;
	}

	public Double getPrimaryProperMotionDec() {
		return primaryProperMotionDec;
	}

	public void setPrimaryProperMotionDec(Double primaryProperMotionDec) {
		this.primaryProperMotionDec = primaryProperMotionDec;
	}

	public Double getSecondaryProperMotionRa() {
		return secondaryProperMotionRa;
	}

	public void setSecondaryProperMotionRa(Double secondaryProperMotionRa) {
		this.secondaryProperMotionRa = secondaryProperMotionRa;
	}

	public Double getSecondaryProperMotionDec() {
		return secondaryProperMotionDec;
	}

	public void setSecondaryProperMotionDec(Double secondaryProperMotionDec) {
		this.secondaryProperMotionDec = secondaryProperMotionDec;
	}

	public String getDurchmusterungNumber() {
		return durchmusterungNumber;
	}

	public void setDurchmusterungNumber(String durchmusterungNumber) {
		this.durchmusterungNumber = durchmusterungNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getArcsecondCoordinates2000() {
		return ArcsecondCoordinates2000;
	}

	public void setArcsecondCoordinates2000(String arcsecondCoordinates2000) {
		ArcsecondCoordinates2000 = arcsecondCoordinates2000;
	}
	
	
}
