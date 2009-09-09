package es.ucm.si.dneb;

import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpImageDownloaderApp {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		
		
		JFrame form = new JFrame();
		form.setSize(640, 480);
		form.setVisible(true);
		
		
		
		//JMenu menu = new JMenu();
		//JMenuItem menuItem= new JMenuItem("aaaa");
		//menuItem.setVisible(true);
		//menu.add(menuItem);
		
		
		//form.add(menu);
		
		//DownloadIt.downloadImage("poss1_red","43","43","J2000","5.0","5.0","fits","none");
	}

}
