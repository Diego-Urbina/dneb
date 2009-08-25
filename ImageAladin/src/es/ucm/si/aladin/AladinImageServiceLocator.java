/**
 * AladinImageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public class AladinImageServiceLocator extends org.apache.axis.client.Service implements es.ucm.si.aladin.AladinImageService {

    public AladinImageServiceLocator() {
    }


    public AladinImageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AladinImageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AladinImage
    private java.lang.String AladinImage_address = "http://cdsws.u-strasbg.fr/axis/services/AladinImage";

    public java.lang.String getAladinImageAddress() {
        return AladinImage_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AladinImageWSDDServiceName = "AladinImage";

    public java.lang.String getAladinImageWSDDServiceName() {
        return AladinImageWSDDServiceName;
    }

    public void setAladinImageWSDDServiceName(java.lang.String name) {
        AladinImageWSDDServiceName = name;
    }

    public es.ucm.si.aladin.AladinImage_PortType getAladinImage() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AladinImage_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAladinImage(endpoint);
    }

    public es.ucm.si.aladin.AladinImage_PortType getAladinImage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.ucm.si.aladin.AladinImageSoapBindingStub _stub = new es.ucm.si.aladin.AladinImageSoapBindingStub(portAddress, this);
            _stub.setPortName(getAladinImageWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAladinImageEndpointAddress(java.lang.String address) {
        AladinImage_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.ucm.si.aladin.AladinImage_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                es.ucm.si.aladin.AladinImageSoapBindingStub _stub = new es.ucm.si.aladin.AladinImageSoapBindingStub(new java.net.URL(AladinImage_address), this);
                _stub.setPortName(getAladinImageWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AladinImage".equals(inputPortName)) {
            return getAladinImage();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:AladinImage", "AladinImageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:AladinImage", "AladinImage"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AladinImage".equals(portName)) {
            setAladinImageEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
