package com.gxp.restito.tests;

import com.gxp.restito.Restito;
import com.gxp.restito.RestitoMatchers;
import static com.gxp.restito.tests.ClientHelper.*;
import static org.hamcrest.CoreMatchers.*;
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

		GET("/some/other/uri");

		Restito.verify(RestitoMatchers.uri(equalTo("/some/uri")));
	}

	@Test
	public void whenSeveralDifferentUrisWereCalledOtherThanWhatWasExpected() {

		GET("/a/uri");
		GET("/another/uri");

		Restito.verify(RestitoMatchers.uri(equalTo("/some/uri")));
	}

	@Test
	public void wrongRequestMethodIsCalledOnCorrectUri() {

		GET("/a/path/to/post/to");

		Restito.verify(RestitoMatchers.request(equalTo("POST"), equalTo("/a/path/to/post/to")));
	}
}
