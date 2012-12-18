package com.gxp.restito;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

/**
 * Collection of tests for the basic concepts of RESTito
 * 
 * @author juboyd
 */
public class ExpectationsTest {
	
	private static final String SOME_LOCALHOST_URL = "http://localhost:9191/some/path";
	
	@Test
	public void knowsWhenUrlWasCalled() {
	
		Restito.expect(SOME_LOCALHOST_URL);
		
		HttpClient client = new DefaultHttpClient();
		client.execute(new HttpGet(SOME_LOCALHOST_URL));
		
		Restito.verify();
	}
	
	@Test(expected=UnsatisifiedServerExpectationException.class)
	public void knowsWhenUrlWasNotCalled() {
		
		Restito.expect(SOME_LOCALHOST_URL);
		
		Restito.verify();
	}
	
	@Test
	public void identifiesUrlThatWasNotCalled() {
		
		Restito.expect(SOME_LOCALHOST_URL);
		
		try {
			Restito.verify();
		} catch (UnsatisifiedServerExpectationException ex) {
			
			assertThat(ex.getMessage(), contains(SOME_LOCALHOST_URL));
		}
	}
	
	@Test
	public void canSetExpectationOfHttpMethod() {
		
	}
	
	@Test
	public void canSetExpectationOnQueryParameters() {
		
	}
	
	@Test
	public void canSetExpectationsOnRequestBody() {
		
	}
}
