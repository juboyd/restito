package com.gxp.restito.tests;

import com.gxp.restito.Restito;
import com.gxp.restito.RestitoMatchers;
import static com.gxp.restito.tests.ClientHelper.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Collection of tests for the basic concepts of RESTito
 *
 * @author juboyd
 */
public class ExpectationsTest {

	private static final String SOME_URI = "/some/path";
	
	@Before
	public void setup() {

		Restito.init(PORT);
	}

	@Test
	public void knowsWhenUrlWasCalled() {

		GET(SOME_URI);

		Restito.verify(RestitoMatchers.uri(equalTo(SOME_URI)));
	}

	@Test(expected = AssertionError.class)
	public void knowsWhenUrlWasNotCalled() {

		Restito.verify(RestitoMatchers.uri(equalTo(SOME_URI)));
	}

	@Test
	public void identifiesUrlThatWasNotCalled() {

		try {
			Restito.verify(RestitoMatchers.uri(equalTo(SOME_URI)));
		} catch (AssertionError ex) {

			assertThat(ex.getMessage(), containsString(SOME_URI));
		}
	}

	@Test
	public void identifiesUrlsThatWereCalledWhenDescribingThatAUrlWasNotCalled() {

		String someOtherUri = "/some/other/uri";
		
		GET(someOtherUri);

		try {
			Restito.verify(RestitoMatchers.uri(equalTo(SOME_URI)));
		} catch (AssertionError ex) {

			assertThat(ex.getMessage(), containsString(someOtherUri));
		}
	}
	
	@Test
	public void canSetExpectationOfHttpMethod() {
		
		GET("/some/path");
		
		Restito.verify(RestitoMatchers.request(equalTo("GET"), equalTo("/some/path")));
	}
	
	@Test(expected=AssertionError.class)
	public void failsWhenWrongMethodAndCorrectUriIsCalled() {
		
		POST("/some/path");
		
		Restito.verify(RestitoMatchers.request(equalTo("GET"), any(String.class)));
	}

//	@Test
//	public void canSetExpectationOnQueryParameters() {
//	}
//
//	@Test
//	public void canSetExpectationsOnRequestBody() {
//	}
}
