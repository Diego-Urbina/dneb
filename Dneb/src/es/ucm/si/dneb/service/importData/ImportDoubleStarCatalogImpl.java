package es.ucm.si.dneb.service.importData;

import org.springframework.stereotype.Service;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service("importDoubleStarCatalog")
public class ImportDoubleStarCatalogImpl implements ImportDoubleStarCatalog{


	public void deleteCatalog() {
		// TODO Auto-generated method stub
		
	}


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
	
	
	public void createCatalog(String path){
		
		ArrayList<String> dsc = this.loadCatalogToString(path);
		
		
	}


	public void updateCatalog(String filePath) {
		// TODO Auto-generated method stub
		
	}
	

}
