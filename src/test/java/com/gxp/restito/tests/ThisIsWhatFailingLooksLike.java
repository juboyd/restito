package com.gxp.restito.tests;

import com.gxp.restito.Restito;
import static com.gxp.restito.tests.ClientHelper.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author juboyd
 */
public class ThisIsWhatFailingLooksLike {

	@Before
	public void setup() {
		Restito.init(9191);
	}
	
	@Test
	public void whenADifferentUriWasCalledThanExpected() {

		String someUri = "/some/uri";
		String someOtherUri = "/some/other/uri";

		Restito.expect(someUri);

		GET(someOtherUri);

		Restito.verify();
	}
	
	@Test
	public void whenSeveralDifferentUrisWereCalledOtherThanWhatWasExpected() {
		
		Restito.expect("/some/uri");

		GET("/a/uri");
		GET("/another/uri");

		Restito.verify();
	}
	
	@Test
	public void wrongRequestMethodIsCalledOnCorrectUri() {
		
		Restito.expect("POST", "/a/path/to/post/to");
		
		GET("/a/path/to/post/to");
		
		Restito.verify();
	}
}
