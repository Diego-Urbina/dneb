/**
 * StorageMapping.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public class StorageMapping  implements java.io.Serializable {
    private int maximumSize;

    private int numberOfPatches;

    private java.lang.String organiation;

    private java.lang.String organisation;

    public StorageMapping() {
    }

    public StorageMapping(
           int maximumSize,
           int numberOfPatches,
           java.lang.String organiation,
           java.lang.String organisation) {
           this.maximumSize = maximumSize;
           this.numberOfPatches = numberOfPatches;
           this.organiation = organiation;
           this.organisation = organisation;
    }


    /**
     * Gets the maximumSize value for this StorageMapping.
     * 
     * @return maximumSize
     */
    public int getMaximumSize() {
        return maximumSize;
    }


    /**
     * Sets the maximumSize value for this StorageMapping.
     * 
     * @param maximumSize
     */
    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }


    /**
     * Gets the numberOfPatches value for this StorageMapping.
     * 
     * @return numberOfPatches
     */
    public int getNumberOfPatches() {
        return numberOfPatches;
    }


    /**
     * Sets the numberOfPatches value for this StorageMapping.
     * 
     * @param numberOfPatches
     */
    public void setNumberOfPatches(int numberOfPatches) {
        this.numberOfPatches = numberOfPatches;
    }


    /**
     * Gets the organiation value for this StorageMapping.
     * 
     * @return organiation
     */
    public java.lang.String getOrganiation() {
        return organiation;
    }


    /**
     * Sets the organiation value for this StorageMapping.
     * 
     * @param organiation
     */
    public void setOrganiation(java.lang.String organiation) {
        this.organiation = organiation;
    }


    /**
     * Gets the organisation value for this StorageMapping.
     * 
     * @return organisation
     */
    public java.lang.String getOrganisation() {
        return organisation;
    }


    /**
     * Sets the organisation value for this StorageMapping.
     * 
     * @param organisation
     */
    public void setOrganisation(java.lang.String organisation) {
        this.organisation = organisation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StorageMapping)) return false;
        StorageMapping other = (StorageMapping) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.maximumSize == other.getMaximumSize() &&
            this.numberOfPatches == other.getNumberOfPatches() &&
            ((this.organiation==null && other.getOrganiation()==null) || 
             (this.organiation!=null &&
              this.organiation.equals(other.getOrganiation()))) &&
            ((this.organisation==null && other.getOrganisation()==null) || 
             (this.organisation!=null &&
              this.organisation.equals(other.getOrganisation())));
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
        _hashCode += getMaximumSize();
        _hashCode += getNumberOfPatches();
        if (getOrganiation() != null) {
            _hashCode += getOrganiation().hashCode();
        }
        if (getOrganisation() != null) {
            _hashCode += getOrganisation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StorageMapping.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "StorageMapping"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maximumSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maximumSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfPatches");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfPatches"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organiation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organiation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organisation"));
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
