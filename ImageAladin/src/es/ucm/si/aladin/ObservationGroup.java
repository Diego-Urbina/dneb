/**
 * ObservationGroup.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public class ObservationGroup  implements java.io.Serializable {
    private es.ucm.si.aladin.Filter[] filters;

    private int filtersCount;

    private es.ucm.si.aladin.Observation[] observations;

    private int observationsCount;

    private java.lang.String[] selectionCriterions;

    private java.lang.String[] selectionRanges;

    public ObservationGroup() {
    }

    public ObservationGroup(
           es.ucm.si.aladin.Filter[] filters,
           int filtersCount,
           es.ucm.si.aladin.Observation[] observations,
           int observationsCount,
           java.lang.String[] selectionCriterions,
           java.lang.String[] selectionRanges) {
           this.filters = filters;
           this.filtersCount = filtersCount;
           this.observations = observations;
           this.observationsCount = observationsCount;
           this.selectionCriterions = selectionCriterions;
           this.selectionRanges = selectionRanges;
    }


    /**
     * Gets the filters value for this ObservationGroup.
     * 
     * @return filters
     */
    public es.ucm.si.aladin.Filter[] getFilters() {
        return filters;
    }


    /**
     * Sets the filters value for this ObservationGroup.
     * 
     * @param filters
     */
    public void setFilters(es.ucm.si.aladin.Filter[] filters) {
        this.filters = filters;
    }


    /**
     * Gets the filtersCount value for this ObservationGroup.
     * 
     * @return filtersCount
     */
    public int getFiltersCount() {
        return filtersCount;
    }


    /**
     * Sets the filtersCount value for this ObservationGroup.
     * 
     * @param filtersCount
     */
    public void setFiltersCount(int filtersCount) {
        this.filtersCount = filtersCount;
    }


    /**
     * Gets the observations value for this ObservationGroup.
     * 
     * @return observations
     */
    public es.ucm.si.aladin.Observation[] getObservations() {
        return observations;
    }


    /**
     * Sets the observations value for this ObservationGroup.
     * 
     * @param observations
     */
    public void setObservations(es.ucm.si.aladin.Observation[] observations) {
        this.observations = observations;
    }


    /**
     * Gets the observationsCount value for this ObservationGroup.
     * 
     * @return observationsCount
     */
    public int getObservationsCount() {
        return observationsCount;
    }


    /**
     * Sets the observationsCount value for this ObservationGroup.
     * 
     * @param observationsCount
     */
    public void setObservationsCount(int observationsCount) {
        this.observationsCount = observationsCount;
    }


    /**
     * Gets the selectionCriterions value for this ObservationGroup.
     * 
     * @return selectionCriterions
     */
    public java.lang.String[] getSelectionCriterions() {
        return selectionCriterions;
    }


    /**
     * Sets the selectionCriterions value for this ObservationGroup.
     * 
     * @param selectionCriterions
     */
    public void setSelectionCriterions(java.lang.String[] selectionCriterions) {
        this.selectionCriterions = selectionCriterions;
    }


    /**
     * Gets the selectionRanges value for this ObservationGroup.
     * 
     * @return selectionRanges
     */
    public java.lang.String[] getSelectionRanges() {
        return selectionRanges;
    }


    /**
     * Sets the selectionRanges value for this ObservationGroup.
     * 
     * @param selectionRanges
     */
    public void setSelectionRanges(java.lang.String[] selectionRanges) {
        this.selectionRanges = selectionRanges;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObservationGroup)) return false;
        ObservationGroup other = (ObservationGroup) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.filters==null && other.getFilters()==null) || 
             (this.filters!=null &&
              java.util.Arrays.equals(this.filters, other.getFilters()))) &&
            this.filtersCount == other.getFiltersCount() &&
            ((this.observations==null && other.getObservations()==null) || 
             (this.observations!=null &&
              java.util.Arrays.equals(this.observations, other.getObservations()))) &&
            this.observationsCount == other.getObservationsCount() &&
            ((this.selectionCriterions==null && other.getSelectionCriterions()==null) || 
             (this.selectionCriterions!=null &&
              java.util.Arrays.equals(this.selectionCriterions, other.getSelectionCriterions()))) &&
            ((this.selectionRanges==null && other.getSelectionRanges()==null) || 
             (this.selectionRanges!=null &&
              java.util.Arrays.equals(this.selectionRanges, other.getSelectionRanges())));
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
        if (getFilters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFilters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFilters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getFiltersCount();
        if (getObservations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObservations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObservations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getObservationsCount();
        if (getSelectionCriterions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectionCriterions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectionCriterions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSelectionRanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectionRanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectionRanges(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObservationGroup.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "ObservationGroup"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filters"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "Filter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filtersCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filtersCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observations");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observations"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AladinImage", "Observation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observationsCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observationsCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectionCriterions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "selectionCriterions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectionRanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "selectionRanges"));
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
