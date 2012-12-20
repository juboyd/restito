package com.gxp.restito.tests;

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author juboyd
 */
public class ClientHelper {
	
	public static final int PORT = 9191;
	public static final String HOST = "localhost";
	
	public static final String BASE_URL = "http://" + HOST + ":" + PORT;

	public static void GET(String uri) {
		try {
			String fullUrl = BASE_URL + uri;
			
			HttpClient client = new DefaultHttpClient();
			client.execute(new HttpGet(fullUrl));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static void POST(String uri) {
		try {
			String fullUrl = BASE_URL + uri;
			
			HttpClient client = new DefaultHttpClient();
			client.execute(new HttpPost(fullUrl));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
