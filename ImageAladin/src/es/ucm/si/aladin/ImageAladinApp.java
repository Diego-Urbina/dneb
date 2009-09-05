package es.ucm.si.aladin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.rpc.ServiceException;

public class ImageAladinApp {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// locator creation
		AladinImageService locator = new AladinImageServiceLocator();
		AladinImage_PortType mysw = locator.getAladinImage();

		
		ObservingProgramDescription[] opd=mysw.getObservingProgramsDescription("+20+20","0.5");
		System.out.println(opd.length);
		for(ObservingProgramDescription x : opd){
			System.out.println(x.getName());
		} 
		
		
		
		
	}

}
