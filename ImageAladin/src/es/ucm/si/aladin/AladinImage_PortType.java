/**
 * AladinImage_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

public interface AladinImage_PortType extends java.rmi.Remote {
    public es.ucm.si.aladin.ObservingProgramDescription[] getObservingProgramsDescription(java.lang.String position, java.lang.String radius) throws java.rmi.RemoteException;
    public es.ucm.si.aladin.ObservingProgram getObservingProgram(java.lang.String position, java.lang.String radius, java.lang.String program_name) throws java.rmi.RemoteException;
    public es.ucm.si.aladin.Filter[] getFilters(java.lang.String position, java.lang.String radius, java.lang.String program_name) throws java.rmi.RemoteException;
    public es.ucm.si.aladin.Observation[] getObservations(java.lang.String position, java.lang.String radius, java.lang.String program_name) throws java.rmi.RemoteException;
    //public java.lang.String[] getImagesLocations(es.ucm.si.aladin.StoredImage[] storedImages, float centralPointRA, float centralPointDEC, java.lang.String processings, java.lang.String codings) throws java.rmi.RemoteException;
    public javax.activation.DataHandler[] getImagesLocations(es.ucm.si.aladin.StoredImage[] storedImages, float centralPointRA, float centralPointDEC, java.lang.String processings, java.lang.String codings) throws java.rmi.RemoteException;
    public java.lang.String getAvailability() throws java.rmi.RemoteException;
}
