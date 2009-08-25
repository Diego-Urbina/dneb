/**
 * Observation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public class Observation  implements java.io.Serializable {
    private float XLim;

    private float YLim;

    private float angularPixelSize;

    private java.lang.String availableCodings;

    private java.lang.String availableProcessings;

    private float centralPointDEC;

    private float centralPointRA;

    private java.lang.String dateAndTime;

    private java.lang.String digitizingMachine;

    private java.lang.String observationName;

    private java.lang.String observationType;

    private java.lang.String origin;

    private java.lang.String originalCoding;

    private java.lang.String ovservationName;

    private float positionAngle;

    private java.lang.String referenceNumber;

    private float sizeAlpha;

    private float sizeDelta;

    private es.ucm.si.aladin.StorageMapping[] storageMappings;

    private int storageMappingsCount;

    private es.ucm.si.aladin.StoredImage[] storedImages;

    private int storedImagesCount;

    public Observation() {
    }

    public Observation(
           float XLim,
           float YLim,
           float angularPixelSize,
           java.lang.String availableCodings,
           java.lang.String availableProcessings,
           float centralPointDEC,
           float centralPointRA,
           java.lang.String dateAndTime,
           java.lang.String digitizingMachine,
           java.lang.String observationName,
           java.lang.String observationType,
           java.lang.String origin,
           java.lang.String originalCoding,
           java.lang.String ovservationName,
           float positionAngle,
           java.lang.String referenceNumber,
           float sizeAlpha,
           float sizeDelta,
           es.ucm.si.aladin.StorageMapping[] storageMappings,
           int storageMappingsCount,
           es.ucm.si.aladin.StoredImage[] storedImages,
           int storedImagesCount) {
           this.XLim = XLim;
           this.YLim = YLim;
           this.angularPixelSize = angularPixelSize;
           this.availableCodings = availableCodings;
           this.availableProcessings = availableProcessings;
           this.centralPointDEC = centralPointDEC;
           this.centralPointRA = centralPointRA;
           this.dateAndTime = dateAndTime;
           this.digitizingMachine = digitizingMachine;
           this.observationName = observationName;
           this.observationType = observationType;
           this.origin = origin;
           this.originalCoding = originalCoding;
           this.ovservationName = ovservationName;
           this.positionAngle = positionAngle;
           this.referenceNumber = referenceNumber;
           this.sizeAlpha = sizeAlpha;
           this.sizeDelta = sizeDelta;
           this.storageMappings = storageMappings;
           this.storageMappingsCount = storageMappingsCount;
           this.storedImages = storedImages;
           this.storedImagesCount = storedImagesCount;
    }


    /**
     * Gets the XLim value for this Observation.
     * 
     * @return XLim
     */
    public float getXLim() {
        return XLim;
    }


    /**
     * Sets the XLim value for this Observation.
     * 
     * @param XLim
     */
    public void setXLim(float XLim) {
        this.XLim = XLim;
    }


    /**
     * Gets the YLim value for this Observation.
     * 
     * @return YLim
     */
    public float getYLim() {
        return YLim;
    }


    /**
     * Sets the YLim value for this Observation.
     * 
     * @param YLim
     */
    public void setYLim(float YLim) {
        this.YLim = YLim;
    }


    /**
     * Gets the angularPixelSize value for this Observation.
     * 
     * @return angularPixelSize
     */
    public float getAngularPixelSize() {
        return angularPixelSize;
    }


    /**
     * Sets the angularPixelSize value for this Observation.
     * 
     * @param angularPixelSize
     */
    public void setAngularPixelSize(float angularPixelSize) {
        this.angularPixelSize = angularPixelSize;
    }


    /**
     * Gets the availableCodings value for this Observation.
     * 
     * @return availableCodings
     */
    public java.lang.String getAvailableCodings() {
        return availableCodings;
    }


    /**
     * Sets the availableCodings value for this Observation.
     * 
     * @param availableCodings
     */
    public void setAvailableCodings(java.lang.String availableCodings) {
        this.availableCodings = availableCodings;
    }


    /**
     * Gets the availableProcessings value for this Observation.
     * 
     * @return availableProcessings
     */
    public java.lang.String getAvailableProcessings() {
        return availableProcessings;
    }


    /**
     * Sets the availableProcessings value for this Observation.
     * 
     * @param availableProcessings
     */
    public void setAvailableProcessings(java.lang.String availableProcessings) {
        this.availableProcessings = availableProcessings;
    }


    /**
     * Gets the centralPointDEC value for this Observation.
     * 
     * @return centralPointDEC
     */
    public float getCentralPointDEC() {
        return centralPointDEC;
    }


    /**
     * Sets the centralPointDEC value for this Observation.
     * 
     * @param centralPointDEC
     */
    public void setCentralPointDEC(float centralPointDEC) {
        this.centralPointDEC = centralPointDEC;
    }


    /**
     * Gets the centralPointRA value for this Observation.
     * 
     * @return centralPointRA
     */
    public float getCentralPointRA() {
        return centralPointRA;
    }


    /**
     * Sets the centralPointRA value for this Observation.
     * 
     * @param centralPointRA
     */
    public void setCentralPointRA(float centralPointRA) {
        this.centralPointRA = centralPointRA;
    }


    /**
     * Gets the dateAndTime value for this Observation.
     * 
     * @return dateAndTime
     */
    public java.lang.String getDateAndTime() {
        return dateAndTime;
    }


    /**
     * Sets the dateAndTime value for this Observation.
     * 
     * @param dateAndTime
     */
    public void setDateAndTime(java.lang.String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }


    /**
     * Gets the digitizingMachine value for this Observation.
     * 
     * @return digitizingMachine
     */
    public java.lang.String getDigitizingMachine() {
        return digitizingMachine;
    }


    /**
     * Sets the digitizingMachine value for this Observation.
     * 
     * @param digitizingMachine
     */
    public void setDigitizingMachine(java.lang.String digitizingMachine) {
        this.digitizingMachine = digitizingMachine;
    }


    /**
     * Gets the observationName value for this Observation.
     * 
     * @return observationName
     */
    public java.lang.String getObservationName() {
        return observationName;
    }


    /**
     * Sets the observationName value for this Observation.
     * 
     * @param observationName
     */
    public void setObservationName(java.lang.String observationName) {
        this.observationName = observationName;
    }


    /**
     * Gets the observationType value for this Observation.
     * 
     * @return observationType
     */
    public java.lang.String getObservationType() {
        return observationType;
    }


    /**
     * Sets the observationType value for this Observation.
     * 
     * @param observationType
     */
    public void setObservationType(java.lang.String observationType) {
        this.observationType = observationType;
    }


    /**
     * Gets the origin value for this Observation.
     * 
     * @return origin
     */
    public java.lang.String getOrigin() {
        return origin;
    }


    /**
     * Sets the origin value for this Observation.
     * 
     * @param origin
     */
    public void setOrigin(java.lang.String origin) {
        this.origin = origin;
    }


    /**
     * Gets the originalCoding value for this Observation.
     * 
     * @return originalCoding
     */
    public java.lang.String getOriginalCoding() {
        return originalCoding;
    }


    /**
     * Sets the originalCoding value for this Observation.
     * 
     * @param originalCoding
     */
    public void setOriginalCoding(java.lang.String originalCoding) {
        this.originalCoding = originalCoding;
    }


    /**
     * Gets the ovservationName value for this Observation.
     * 
     * @return ovservationName
     */
    public java.lang.String getOvservationName() {
        return ovservationName;
    }


    /**
     * Sets the ovservationName value for this Observation.
     * 
     * @param ovservationName
     */
    public void setOvservationName(java.lang.String ovservationName) {
        this.ovservationName = ovservationName;
    }


    /**
     * Gets the positionAngle value for this Observation.
     * 
     * @return positionAngle
     */
    public float getPositionAngle() {
        return positionAngle;
    }


    /**
     * Sets the positionAngle value for this Observation.
     * 
     * @param positionAngle
     */
    public void setPositionAngle(float positionAngle) {
        this.positionAngle = positionAngle;
    }


    /**
     * Gets the referenceNumber value for this Observation.
     * 
     * @return referenceNumber
     */
    public java.lang.String getReferenceNumber() {
        return referenceNumber;
    }


    /**
     * Sets the referenceNumber value for this Observation.
     * 
     * @param referenceNumber
     */
    public void setReferenceNumber(java.lang.String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }


    /**
     * Gets the sizeAlpha value for this Observation.
     * 
     * @return sizeAlpha
     */
    public float getSizeAlpha() {
        return sizeAlpha;
    }


    /**
     * Sets the sizeAlpha value for this Observation.
     * 
     * @param sizeAlpha
     */
    public void setSizeAlpha(float sizeAlpha) {
        this.sizeAlpha = sizeAlpha;
    }


    /**
     * Gets the sizeDelta value for this Observation.
     * 
     * @return sizeDelta
     */
    public float getSizeDelta() {
        return sizeDelta;
    }


    /**
     * Sets the sizeDelta value for this Observation.
     * 
     * @param sizeDelta
     */
    public void setSizeDelta(float sizeDelta) {
        this.sizeDelta = sizeDelta;
    }


    /**
     * Gets the storageMappings value for this Observation.
     * 
     * @return storageMappings
     */
    public es.ucm.si.aladin.StorageMapping[] getStorageMappings() {
        return storageMappings;
    }


    /**
     * Sets the storageMappings value for this Observation.
     * 
     * @param storageMappings
     */
    public void setStorageMappings(es.ucm.si.aladin.StorageMapping[] storageMappings) {
        this.storageMappings = storageMappings;
    }


    /**
     * Gets the storageMappingsCount value for this Observation.
     * 
     * @return storageMappingsCount
     */
    public int getStorageMappingsCount() {
        return storageMappingsCount;
    }


    /**
     * Sets the storageMappingsCount value for this Observation.
     * 
     * @param storageMappingsCount
     */
    public void setStorageMappingsCount(int storageMappingsCount) {
        this.storageMappingsCount = storageMappingsCount;
    }


    /**
     * Gets the storedImages value for this Observation.
     * 
     * @return storedImages
     */
    public es.ucm.si.aladin.StoredImage[] getStoredImages() {
        return storedImages;
    }


    /**
     * Sets the storedImages value for this Observation.
     * 
     * @param storedImages
     */
    public void setStoredImages(es.ucm.si.aladin.StoredImage[] storedImages) {
        this.storedImages = storedImages;
    }


    /**
     * Gets the storedImagesCount value for this Observation.
     * 
     * @return storedImagesCount
     */
    public int getStoredImagesCount() {
        return storedImagesCount;
    }


    /**
     * Sets the storedImagesCount value for this Observation.
     * 
     * @param storedImagesCount
     */
    public void setStoredImagesCount(int storedImagesCount) {
        this.storedImagesCount = storedImagesCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Observation)) return false;
        Observation other = (Observation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.XLim == other.getXLim() &&
            this.YLim == other.getYLim() &&
            this.angularPixelSize == other.getAngularPixelSize() &&
            ((this.availableCodings==null && other.getAvailableCodings()==null) || 
             (this.availableCodings!=null &&
              this.availableCodings.equals(other.getAvailableCodings()))) &&
            ((this.availableProcessings==null && other.getAvailableProcessings()==null) || 
             (this.availableProcessings!=null &&
              this.availableProcessings.equals(other.getAvailableProcessings()))) &&
            this.centralPointDEC == other.getCentralPointDEC() &&
            this.centralPointRA == other.getCentralPointRA() &&
            ((this.dateAndTime==null && other.getDateAndTime()==null) || 
             (this.dateAndTime!=null &&
              this.dateAndTime.equals(other.getDateAndTime()))) &&
            ((this.digitizingMachine==null && other.getDigitizingMachine()==null) || 
             (this.digitizingMachine!=null &&
              this.digitizingMachine.equals(other.getDigitizingMachine()))) &&
            ((this.observationName==null && other.getObservationName()==null) || 
             (this.observationName!=null &&
              this.observationName.equals(other.getObservationName()))) &&
            ((this.observationType==null && other.getObservationType()==null) || 
             (this.observationType!=null &&
              this.observationType.equals(other.getObservationType()))) &&
            ((this.origin==null && other.getOrigin()==null) || 
             (this.origin!=null &&
              this.origin.equals(other.getOrigin()))) &&
            ((this.originalCoding==null && other.getOriginalCoding()==null) || 
             (this.originalCoding!=null &&
              this.originalCoding.equals(other.getOriginalCoding()))) &&
            ((this.ovservationName==null && other.getOvservationName()==null) || 
             (this.ovservationName!=null &&
              this.ovservationName.equals(other.getOvservationName()))) &&
            this.positionAngle == other.getPositionAngle() &&
            ((this.referenceNumber==null && other.getReferenceNumber()==null) || 
             (this.referenceNumber!=null &&
              this.referenceNumber.equals(other.getReferenceNumber()))) &&
            this.sizeAlpha == other.getSizeAlpha() &&
            this.sizeDelta == other.getSizeDelta() &&
            ((this.storageMappings==null && other.getStorageMappings()==null) || 
             (this.storageMappings!=null &&
              java.util.Arrays.equals(this.storageMappings, other.getStorageMappings()))) &&
            this.storageMappingsCount == other.getStorageMappingsCount() &&
            ((this.storedImages==null && other.getStoredImages()==null) || 
             (this.storedImages!=null &&
              java.util.Arrays.equals(this.storedImages, other.getStoredImages()))) &&
            this.storedImagesCount == other.getStoredImagesCount();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Float(getXLim()).hashCode();
        _hashCode += new Float(getYLim()).hashCode();
        _hashCode += new Float(getAngularPixelSize()).hashCode();
        if (getAvailableCodings() != null) {
            _hashCode += getAvailableCodings().hashCode();
        }
        if (getAvailableProcessings() != null) {
            _hashCode += getAvailableProcessings().hashCode();
        }
        _hashCode += new Float(getCentralPointDEC()).hashCode();
        _hashCode += new Float(getCentralPointRA()).hashCode();
        if (getDateAndTime() != null) {
            _hashCode += getDateAndTime().hashCode();
        }
        if (getDigitizingMachine() != null) {
            _hashCode += getDigitizingMachine().hashCode();
        }
        if (getObservationName() != null) {
            _hashCode += getObservationName().hashCode();
        }
        if (getObservationType() != null) {
            _hashCode += getObservationType().hashCode();
        }
        if (getOrigin() != null) {
            _hashCode += getOrigin().hashCode();
        }
        if (getOriginalCoding() != null) {
            _hashCode += getOriginalCoding().hashCode();
        }
        if (getOvservationName() != null) {
            _hashCode += getOvservationName().hashCode();
        }
        _hashCode += new Float(getPositionAngle()).hashCode();
        if (getReferenceNumber() != null) {
            _hashCode += getReferenceNumber().hashCode();
        }
        _hashCode += new Float(getSizeAlpha()).hashCode();
        _hashCode += new Float(getSizeDelta()).hashCode();
        if (getStorageMappings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStorageMappings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStorageMappings(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getStorageMappingsCount();
        if (getStoredImages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStoredImages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStoredImages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getStoredImagesCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Observation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "Observation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XLim");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XLim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YLim");
        elemField.setXmlName(new javax.xml.namespace.QName("", "YLim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("angularPixelSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "angularPixelSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableCodings");
        elemField.setXmlName(new javax.xml.namespace.QName("", "availableCodings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableProcessings");
        elemField.setXmlName(new javax.xml.namespace.QName("", "availableProcessings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centralPointDEC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centralPointDEC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centralPointRA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centralPointRA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateAndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateAndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digitizingMachine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "digitizingMachine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observationName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalCoding");
        elemField.setXmlName(new javax.xml.namespace.QName("", "originalCoding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ovservationName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ovservationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positionAngle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "positionAngle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sizeAlpha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sizeAlpha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sizeDelta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sizeDelta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storageMappings");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storageMappings"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "StorageMapping"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storageMappingsCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storageMappingsCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storedImages");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storedImages"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "StoredImage"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storedImagesCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storedImagesCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
