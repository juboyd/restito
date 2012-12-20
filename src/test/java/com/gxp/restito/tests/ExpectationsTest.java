package com.gxp.restito.tests;

import com.gxp.restito.Restito;
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

		Restito.expect(SOME_URI);

		GET(SOME_URI);

		Restito.verify();
	}

	@Test(expected = AssertionError.class)
	public void knowsWhenUrlWasNotCalled() {

		Restito.expect(SOME_URI);

		Restito.verify();
	}

	@Test
	public void identifiesUrlThatWasNotCalled() {

		Restito.expect(SOME_URI);

		try {
			Restito.verify();
		} catch (AssertionError ex) {

			assertThat(ex.getMessage(), containsString(SOME_URI));
		}
	}

	@Test
	public void identifiesUrlsThatWereCalledWhenDescribingThatAUrlWasNotCalled() {

		String someOtherUri = "/some/other/uri";
		
		Restito.expect(SOME_URI);

		GET(someOtherUri);

		try {
			Restito.verify();
		} catch (AssertionError ex) {

			assertThat(ex.getMessage(), containsString(someOtherUri));
		}
	}
	
	@Test
	public void canSetExpectationOfHttpMethod() {
	
		Restito.expect("GET", "/some/path");
		
		GET("/some/path");
		
		Restito.verify();
	}
	
	@Test(expected=AssertionError.class)
	public void failsWhenWrongMethodAndCorrectUriIsCalled() {
		
		Restito.expect("GET", "/some/path");
		
		POST("/some/path");
		
		Restito.verify();
	}

//	@Test
//	public void canSetExpectationOnQueryParameters() {
//	}
//
//	@Test
//	public void canSetExpectationsOnRequestBody() {
//	}
}
