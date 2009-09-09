package es.ucm.si.dneb;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
		/*formparams.add(new BasicNameValuePair("v", "poss2ukstu_red"));
		formparams.add(new BasicNameValuePair("r", "43"));
		formparams.add(new BasicNameValuePair("d", "43"));
		formparams.add(new BasicNameValuePair("e", "J2000"));
		formparams.add(new BasicNameValuePair("h", "15.0"));
		formparams.add(new BasicNameValuePair("w", "15.0"));
		formparams.add(new BasicNameValuePair("f", "fits"));
		formparams.add(new BasicNameValuePair("c", "none"));
		formparams.add(new BasicNameValuePair("fov", "none"));
		formparams.add(new BasicNameValuePair("v3", ""));*/
		DownloadIt.downloadImage("poss2ukstu_red","43","43","J2000","5.0","5.0","fits","none");
	}

}
