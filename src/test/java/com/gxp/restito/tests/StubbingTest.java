package com.gxp.restito.tests;

import com.gxp.restito.RestitoMatchers;
import com.gxp.restito.Restito;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static com.gxp.restito.tests.RestitoUnitTestMatchers.*;
import java.io.IOException;

/**
 *
 * @author juboyd
 */
public class StubbingTest {

	@Test
	public void canStubDefaultResponse() throws IOException {
		
//		Restito.stub(RestitoMatchers.anyRequest(), HttpStatus.SC_ACCEPTED);
//		
//		HttpClient client = new DefaultHttpClient();
//		HttpResponse response = client.execute(new HttpGet("http://localhost:9191/some/path"));
//		
//		assertThat(response, hasStatusCode(HttpStatus.SC_ACCEPTED));
	}
}
