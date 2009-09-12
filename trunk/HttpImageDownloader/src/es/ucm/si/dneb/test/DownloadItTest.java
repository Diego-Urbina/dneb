package es.ucm.si.dneb.test;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.junit.*;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import es.ucm.si.dneb.ImageDownloader;

public class DownloadItTest {
	
	private static final  Log LOG = LogFactory.getLog(DownloadItTest.class);
	static ImageDownloader imageDownloader;
	
	@BeforeClass
    public static void setUpClass() throws Exception {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            imageDownloader =(ImageDownloader)ctx.getBean("ImageDownloader");
                } catch (Throwable e) {
                        e.printStackTrace();
                }
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Test
    public void pos1RedTest() throws ClientProtocolException, IOException{
    	imageDownloader.downloadImage("poss1_red","43","43","J2000","5.0","5.0","fits","none");
    	
    }
    
}
