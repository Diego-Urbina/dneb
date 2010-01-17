package es.ucm.si.dneb.service.importData;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DoubleStarCatalog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("importDoubleStarCatalog")
public class ImportDoubleStarCatalogImpl implements ImportDoubleStarCatalog{

	@PersistenceContext
	EntityManager manager;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteCatalog() {
		// TODO Auto-generated method stub
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public ArrayList<String> loadCatalogToString(String fileName) {
		
		  	 if ((fileName == null) || (fileName == ""))
		            throw new IllegalArgumentException();
		        
		        String line;
		        ArrayList<String>  file = new ArrayList<String> ();

		        try
		        {    
		            BufferedReader in = new BufferedReader(new FileReader(fileName));

		            if (!in.ready())
		                throw new IOException();

		            while ((line = in.readLine()) != null)
		                file.add(line);

		            in.close();
		        }
		        catch (IOException e)
		        {
		            System.out.println(e);
		            return null;
		        }

		        return file;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void loadCatalog(String path){
		
		ArrayList<String> dsc = this.loadCatalogToString(path);
		
		
		
		for(String doubleStar : dsc ){	
			
			
				//TODO
				DoubleStarCatalog doubleStarCatalog= new DoubleStarCatalog();
				
				doubleStarCatalog.setCoordinates(doubleStar.substring(0, 10));
				doubleStarCatalog.setDiscovererAndNumber(doubleStar.substring(10, 17));
				doubleStarCatalog.setComponents(doubleStar.substring(17,22));
				doubleStarCatalog.setFirstObservation(Date.valueOf(doubleStar.substring(24,27).trim()+"-01-01"));
				//doubleStarCatalog.setLastObservation(doubleStar.substring(28,32))
				doubleStarCatalog.setNumObservations(Integer.decode(doubleStar.substring(33,37).trim()));
				//doubleStarCatalog.setFirstPosAngle(Double.parseDouble(doubleStar.substring(38,42).trim()));
				
				manager.persist(doubleStarCatalog);
			
			
		}
		
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCatalog(String filePath) {
		// TODO Auto-generated method stub
		
	}
	

}
