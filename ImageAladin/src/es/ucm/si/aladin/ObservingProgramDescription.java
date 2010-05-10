/**
 * ObservingProgramDescription.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public class ObservingProgramDescription  implements java.io.Serializable {
    private java.lang.String beginningDate;

    private java.lang.String endDate;

    private java.lang.String name;

    private java.lang.String organisation;

    private java.lang.String spectralCoverageName;

    public ObservingProgramDescription() {
    }

    public ObservingProgramDescription(
           java.lang.String beginningDate,
           java.lang.String endDate,
           java.lang.String name,
           java.lang.String organisation,
           java.lang.String spectralCoverageName) {
           this.beginningDate = beginningDate;
           this.endDate = endDate;
           this.name = name;
           this.organisation = organisation;
           this.spectralCoverageName = spectralCoverageName;
    }


    /**
     * Gets the beginningDate value for this ObservingProgramDescription.
     * 
     * @return beginningDate
     */
    public java.lang.String getBeginningDate() {
        return beginningDate;
    }


    /**
     * Sets the beginningDate value for this ObservingProgramDescription.
     * 
     * @param beginningDate
     */
    public void setBeginningDate(java.lang.String beginningDate) {
        this.beginningDate = beginningDate;
    }


    /**
     * Gets the endDate value for this ObservingProgramDescription.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this ObservingProgramDescription.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the name value for this ObservingProgramDescription.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ObservingProgramDescription.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the organisation value for this ObservingProgramDescription.
     * 
     * @return organisation
     */
    public java.lang.String getOrganisation() {
        return organisation;
    }


    /**
     * Sets the organisation value for this ObservingProgramDescription.
     * 
     * @param organisation
     */
    public void setOrganisation(java.lang.String organisation) {
        this.organisation = organisation;
    }


    /**
     * Gets the spectralCoverageName value for this ObservingProgramDescription.
     * 
     * @return spectralCoverageName
     */
    public java.lang.String getSpectralCoverageName() {
        return spectralCoverageName;
    }


    /**
     * Sets the spectralCoverageName value for this ObservingProgramDescription.
     * 
     * @param spectralCoverageName
     */
    public void setSpectralCoverageName(java.lang.String spectralCoverageName) {
        this.spectralCoverageName = spectralCoverageName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObservingProgramDescription)) return false;
        ObservingProgramDescription other = (ObservingProgramDescription) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.beginningDate==null && other.getBeginningDate()==null) || 
             (this.beginningDate!=null &&
              this.beginningDate.equals(other.getBeginningDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.organisation==null && other.getOrganisation()==null) || 
             (this.organisation!=null &&
              this.organisation.equals(other.getOrganisation()))) &&
            ((this.spectralCoverageName==null && other.getSpectralCoverageName()==null) || 
             (this.spectralCoverageName!=null &&
              this.spectralCoverageName.equals(other.getSpectralCoverageName())));
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
        if (getBeginningDate() != null) {
            _hashCode += getBeginningDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getOrganisation() != null) {
            _hashCode += getOrganisation().hashCode();
        }
        if (getSpectralCoverageName() != null) {
            _hashCode += getSpectralCoverageName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObservingProgramDescription.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "ObservingProgramDescription"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginningDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginningDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spectralCoverageName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spectralCoverageName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
