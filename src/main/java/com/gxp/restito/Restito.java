package com.gxp.restito;

import com.gxp.restito.request.Request;
import com.gxp.restito.server.RestitoServer;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.gxp.restito.RestitoMatchers.*;
import org.hamcrest.Matcher;

/**
 * Provides static methods for building expectations and verifying
 *
 * @author juboyd
 */
public class Restito {

	private static RestitoServer server;
	private static Journal journal;
	private static List<Matcher<? super Request>> expectations;

	public static void init(int port) {

		if (server != null) {
			server.stop();
		}

		journal = new Journal();
		expectations = new LinkedList<Matcher<? super Request>>();
		server = new RestitoServer(port, journal);

		server.run();
	}

	public static void expect(String uri) {

		expectations.add(requestWithUriThat(equalToIgnoringCase(uri)));
	}

	public static void expect(String method, String uri) {

		expectations.add(request(equalToIgnoringCase(method), equalToIgnoringCase(uri)));
	}

	public static void verify() {

		for (Matcher<? super Request> matcher : expectations) {

			assertThat(journal, hasItem(matcher));
		}
	}
	private static final Logger logger = LoggerFactory.getLogger(Restito.class.getName());
}
