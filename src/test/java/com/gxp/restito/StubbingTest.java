package com.gxp.restito;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static com.gxp.restito.RestitoUnitTestMatchers.*;

/**
 *
 * @author juboyd
 */
public class StubbingTest {

	@Test
	public void canStubDefaultResponse() {
		
		Restito.stub(RestitoMatchers.anyRequest(), HttpStatus.SC_ACCEPTED);
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(new HttpGet("http://localhost:9191/some/path"));
		
		assertThat(response, hasStatusCode(HttpStatus.SC_ACCEPTED));
	}
}
