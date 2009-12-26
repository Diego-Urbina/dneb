package es.ucm.si.dneb.service.inicializador;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextoAplicacion {
	
	private static ApplicationContext ctx = null; //new ClassPathXmlApplicationContext("applicationContext.xml");
	public static ApplicationContext getApplicationContext() {
        if (ctx == null){
        	ctx = new ClassPathXmlApplicationContext("applicationContext.xml");    
        }
        return ctx;
        	
    }


}
