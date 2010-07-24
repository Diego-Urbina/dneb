package es.ucm.si.dneb.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

public class FiltreExtensible extends FileFilter{
	
	   // descripción y extensiones aceptadas por el filtro
	   private String description;
	   private List<String> extensions;
	 
	   // constructor a partir de la descripción
	   public FiltreExtensible(String description){
	      this.description = description;
	      this.extensions = new ArrayList<String>();
	   }
	 
	   public String getDescription(){
		  if (description == null)
			  return "No description";
		  
	      StringBuffer buffer = new StringBuffer(description);
	      buffer.append(" (");
	      for(String extension : extensions){
	         buffer.append(extension).append(" ");
	      }
	      return buffer.append(")").toString();
	   }
	 
	   public void setDescription(String description){
	      this.description = description;
	   }
	 
	   public void addExtension(String extension){
	      if(extension == null){
	         throw new RuntimeException("La extensión no puede ser null.");
	      }
	      extensions.add(extension);
	   }
	 
	   public void removeExtension(String extension){
	      extensions.remove(extension);
	   }
	 
	   public void clearExtensions(){
	      extensions.clear();
	   }
	 
	   public List<String> getExtensions(){
	      return extensions;
	   }
	
		@Override
		public boolean accept(File file) {
			if(file.isDirectory() || extensions.size()==0) { 
		         return true; 
		    } 
		    String nombreFichero = file.getName().toLowerCase(); 
		    for(String extension : extensions){
		       if(nombreFichero.endsWith(extension)){
		          return true;
		       }
		    }
		    return false;
		}
}