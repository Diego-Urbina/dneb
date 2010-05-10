/**
 * Filter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public class Filter  implements java.io.Serializable {
    private java.lang.String bandPathName;

    private float effectiveWaveLength;

    private java.lang.String identifier;

    private float maximalWaveLength;

    private float minimalWaveLength;

    public Filter() {
    }

    public Filter(
           java.lang.String bandPathName,
           float effectiveWaveLength,
           java.lang.String identifier,
           float maximalWaveLength,
           float minimalWaveLength) {
           this.bandPathName = bandPathName;
           this.effectiveWaveLength = effectiveWaveLength;
           this.identifier = identifier;
           this.maximalWaveLength = maximalWaveLength;
           this.minimalWaveLength = minimalWaveLength;
    }


    /**
     * Gets the bandPathName value for this Filter.
     * 
     * @return bandPathName
     */
    public java.lang.String getBandPathName() {
        return bandPathName;
    }


    /**
     * Sets the bandPathName value for this Filter.
     * 
     * @param bandPathName
     */
    public void setBandPathName(java.lang.String bandPathName) {
        this.bandPathName = bandPathName;
    }


    /**
     * Gets the effectiveWaveLength value for this Filter.
     * 
     * @return effectiveWaveLength
     */
    public float getEffectiveWaveLength() {
        return effectiveWaveLength;
    }


    /**
     * Sets the effectiveWaveLength value for this Filter.
     * 
     * @param effectiveWaveLength
     */
    public void setEffectiveWaveLength(float effectiveWaveLength) {
        this.effectiveWaveLength = effectiveWaveLength;
    }


    /**
     * Gets the identifier value for this Filter.
     * 
     * @return identifier
     */
    public java.lang.String getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this Filter.
     * 
     * @param identifier
     */
    public void setIdentifier(java.lang.String identifier) {
        this.identifier = identifier;
    }


    /**
     * Gets the maximalWaveLength value for this Filter.
     * 
     * @return maximalWaveLength
     */
    public float getMaximalWaveLength() {
        return maximalWaveLength;
    }


    /**
     * Sets the maximalWaveLength value for this Filter.
     * 
     * @param maximalWaveLength
     */
    public void setMaximalWaveLength(float maximalWaveLength) {
        this.maximalWaveLength = maximalWaveLength;
    }


    /**
     * Gets the minimalWaveLength value for this Filter.
     * 
     * @return minimalWaveLength
     */
    public float getMinimalWaveLength() {
        return minimalWaveLength;
    }


    /**
     * Sets the minimalWaveLength value for this Filter.
     * 
     * @param minimalWaveLength
     */
    public void setMinimalWaveLength(float minimalWaveLength) {
        this.minimalWaveLength = minimalWaveLength;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Filter)) return false;
        Filter other = (Filter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bandPathName==null && other.getBandPathName()==null) || 
             (this.bandPathName!=null &&
              this.bandPathName.equals(other.getBandPathName()))) &&
            this.effectiveWaveLength == other.getEffectiveWaveLength() &&
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              this.identifier.equals(other.getIdentifier()))) &&
            this.maximalWaveLength == other.getMaximalWaveLength() &&
            this.minimalWaveLength == other.getMinimalWaveLength();
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
        if (getBandPathName() != null) {
            _hashCode += getBandPathName().hashCode();
        }
        _hashCode += new Float(getEffectiveWaveLength()).hashCode();
        if (getIdentifier() != null) {
            _hashCode += getIdentifier().hashCode();
        }
        _hashCode += new Float(getMaximalWaveLength()).hashCode();
        _hashCode += new Float(getMinimalWaveLength()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Filter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "Filter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bandPathName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bandPathName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectiveWaveLength");
        elemField.setXmlName(new javax.xml.namespace.QName("", "effectiveWaveLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maximalWaveLength");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maximalWaveLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minimalWaveLength");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minimalWaveLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
