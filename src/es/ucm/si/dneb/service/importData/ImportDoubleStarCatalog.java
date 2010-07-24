package es.ucm.si.dneb.service.importData;

import java.util.ArrayList;

public interface ImportDoubleStarCatalog {
	
	
	public void deleteCatalog();
	public ArrayList<String> loadCatalogToString(String fileName);
	public void updateCatalog(String filePath);
	public void loadCatalog(String path);

}
