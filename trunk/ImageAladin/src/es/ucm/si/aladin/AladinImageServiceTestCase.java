/**
 * AladinImageServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.ucm.si.aladin;

import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.xml.rpc.ServiceException;

public class AladinImageServiceTestCase extends junit.framework.TestCase {
	public AladinImageServiceTestCase(java.lang.String name) {
		super(name);
	}

	public void testAladinImageWSDL() throws Exception {
		javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory
				.newInstance();
		java.net.URL url = new java.net.URL(
				new es.ucm.si.aladin.AladinImageServiceLocator()
						.getAladinImageAddress()
						+ "?WSDL");
		javax.xml.rpc.Service service = serviceFactory.createService(url,
				new es.ucm.si.aladin.AladinImageServiceLocator()
						.getServiceName());
		assertTrue(service != null);
	}

	public void test1AladinImageGetObservingProgramsDescription()
			throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		es.ucm.si.aladin.ObservingProgramDescription[] value = null;
		value = binding.getObservingProgramsDescription(new java.lang.String(),
				new java.lang.String());
		// TBD - validate results
	}

	public void testWeb() throws ServiceException, RemoteException {

		// locator creation
		AladinImageService locator = new AladinImageServiceLocator();
		AladinImage_PortType mysw = locator.getAladinImage();

		// example with getObservingProgram
		ObservingProgram op = mysw.getObservingProgram("053.11661-27.80931",
				"0.5", "GOODS-ACIS");
		System.out.println("Observation Groups Count : "
				+ op.getObservationGroupsCount());

		// example with getObservations
		Observation[] o = mysw.getObservations("053.11661-27.80931", "0.5",
				"GOODS-ACIS");
		for (int i = 0; i < o.length; i++) {
			System.out.println("Name : " + o[i].getObservationName()
					+ " Available codings : " + o[i].getAvailableCodings());
		}
		/** TODO **/
		StoredImage[] si = o[0].getStoredImages();

		System.out.println(o[0].getCentralPointDEC());
		System.out.println(o[0].getCentralPointRA());
		System.out.println(o[0].getStoredImagesCount());

		String[] dh = mysw.getImagesLocations(si,o[0].getCentralPointRA(), o[0].getCentralPointDEC(), "", "FITS");
		
		for(String x: dh){
			
			System.out.println(x);
		}
		// example with getFilters
		Filter[] f = mysw.getFilters("053.11661-27.80931", "0.5", "GOODS-ACIS");

	}

	public void test2AladinImageGetObservingProgram() throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		es.ucm.si.aladin.ObservingProgram value = null;
		value = binding.getObservingProgram(new java.lang.String(),
				new java.lang.String(), new java.lang.String());
		// TBD - validate results
	}

	public void test3AladinImageGetFilters() throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		es.ucm.si.aladin.Filter[] value = null;
		value = binding.getFilters(new java.lang.String(),
				new java.lang.String(), new java.lang.String());
		// TBD - validate results
	}

	public void test4AladinImageGetObservations() throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		es.ucm.si.aladin.Observation[] value = null;
		value = binding.getObservations(new java.lang.String(),
				new java.lang.String(), new java.lang.String());
		// TBD - validate results
	}

	public void test5AladinImageGetImagesLocations() throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		java.lang.String[] value = null;
		value = binding.getImagesLocations(new es.ucm.si.aladin.StoredImage[0],
				0, 0, new java.lang.String(), new java.lang.String());
		// TBD - validate results
	}

	public void test6AladinImageGetImagesLocations() throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		javax.activation.DataHandler[] value = null;
		/*value = binding.getImagesLocations(new es.ucm.si.aladin.StoredImage[0],
				0, 0, new java.lang.String(), new java.lang.String());*/
		// TBD - validate results
	}

	public void test7AladinImageGetAvailability() throws Exception {
		es.ucm.si.aladin.AladinImageSoapBindingStub binding;
		try {
			binding = (es.ucm.si.aladin.AladinImageSoapBindingStub) new es.ucm.si.aladin.AladinImageServiceLocator()
					.getAladinImage();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		java.lang.String value = null;
		value = binding.getAvailability();
		// TBD - validate results
	}

}
