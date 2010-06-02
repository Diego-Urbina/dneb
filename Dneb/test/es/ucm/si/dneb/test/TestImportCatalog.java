package es.ucm.si.dneb.test;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.service.importData.ImportDoubleStarCatalog;

public class TestImportCatalog {
	
	static ImportDoubleStarCatalog importDoubleStarCatalog;	
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		/**CREACIÓN DE LOS BEANS DE SPRING**/
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			importDoubleStarCatalog = (ImportDoubleStarCatalog) ctx.getBean("importDoubleStarCatalog");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		/**CREACION DE ELEMENTOS PARA PODER REALIZAR LAS PRUEBAS**/
		
	}
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	
	public void loadDataToStringTest(){
		 ArrayList<String> bob = importDoubleStarCatalog.loadCatalogToString("D:/DESARROLLO/eclipseworkspace/Dneb/docs/wds.txt");
	        for (int i=0; i<bob.size(); i++)
	            System.out.println(i+1 + ":\t" + bob.get(i));
		
		
	}
	
	@Test
	public void loadDataToDDBB(){
		
		importDoubleStarCatalog.loadCatalog("D:/DESARROLLO/eclipseworkspace/Dneb/docs/wds.txt");
	
	}

}
