package es.ucm.si.dneb.service.math;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MathServiceTest {
	
	
	static MathService mathService ;
	private static final Log LOG = LogFactory.getLog(MathServiceTest.class);
	@BeforeClass
	public static void setUpClass() throws Exception {
		/**CREACIÓN DE LOS BEANS DE SPRING**/
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			mathService = (MathService) ctx.getBean("mathService");
		}catch (Throwable e) {
			e.printStackTrace();
		}
			
	}
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Test
	public void testCaculateDistance(){
		Distance distance=mathService.calculateDecimalDistance(56.203714,57.028294, 56.197816, 57.031528);
		LOG.debug("DISTANCE "+distance.getDistance());
		LOG.debug("ANGLE "+distance.getAngle());
		
	}
}
