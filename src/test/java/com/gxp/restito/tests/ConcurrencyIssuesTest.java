package com.gxp.restito.tests;

import com.gxp.restito.Restito;
import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

/**
 *
 * @author juboyd
 */
public class ConcurrencyIssuesTest {

	@Test
	public void setup() {
		Restito.init(9191);
		performGetRequest("/some/path");
		performGetRequest("/some/other/path");
		Restito.init(9191);
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		Restito.init(9191);
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");

	}
	
	@Test
	public void setup1() {
		Restito.init(9191);
		performGetRequest("/some/path");
		performGetRequest("/some/other/path");
		Restito.init(9191);
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		Restito.init(9191);
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");
		performGetRequest("yet/another/path");
		performGetRequest("yup/another/path");

	}

	private void performGetRequest(String uri) {
		try {
			HttpClient client = new DefaultHttpClient();
			client.execute(new HttpGet("http://localhost:9191" + uri));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
