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
	
	@Column(name="AR_GRADOS")
	private double ascRecGrados;
	
	@Column(name="DEC_GRADOS")
	private double decGrados;
	
	@Column(name="ARCH_COORD_2000")
	private String arcsecondCoordinates2000;

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
		return arcsecondCoordinates2000;
	}

	public void setArcsecondCoordinates2000(String arcsecondCoordinates2000) {
		this.arcsecondCoordinates2000 = arcsecondCoordinates2000;
	}

	public void setAscRecGrados(double ascRecGrados) {
		this.ascRecGrados = ascRecGrados;
	}

	public double getAscRecGrados() {
		return ascRecGrados;
	}

	public void setDecGrados(double decGrados) {
		this.decGrados = decGrados;
	}

	public double getDecGrados() {
		return decGrados;
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
	    
	    retValue = "DoubleStarCatalog ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "coordinates = " + this.coordinates + TAB
	        + "discovererAndNumber = " + this.discovererAndNumber + TAB
	        + "components = " + this.components + TAB
	        + "firstObservation = " + this.firstObservation + TAB
	        + "lastObservation = " + this.lastObservation + TAB
	        + "numObservations = " + this.numObservations + TAB
	        + "firstPosAngle = " + this.firstPosAngle + TAB
	        + "lastPosAnges = " + this.lastPosAnges + TAB
	        + "firstSeparation = " + this.firstSeparation + TAB
	        + "lastSeparation = " + this.lastSeparation + TAB
	        + "firstStarMagnitude = " + this.firstStarMagnitude + TAB
	        + "secondStarMagnitude = " + this.secondStarMagnitude + TAB
	        + "spectralType = " + this.spectralType + TAB
	        + "primaryProperMotionRa = " + this.primaryProperMotionRa + TAB
	        + "primaryProperMotionDec = " + this.primaryProperMotionDec + TAB
	        + "secondaryProperMotionRa = " + this.secondaryProperMotionRa + TAB
	        + "secondaryProperMotionDec = " + this.secondaryProperMotionDec + TAB
	        + "durchmusterungNumber = " + this.durchmusterungNumber + TAB
	        + "notes = " + this.notes + TAB
	        + "ascRecGrados = " + this.ascRecGrados + TAB
	        + "decGrados = " + this.decGrados + TAB
	        + "arcsecondCoordinates2000 = " + this.arcsecondCoordinates2000 + TAB
	        + " )";
	
	    return retValue;
	}

	
	
	
}
