package com.gxp.restito;

import com.gxp.restito.request.Request;
import com.gxp.restito.server.RestitoServer;
import org.hamcrest.Matcher;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides static methods for building expectations and verifying
 *
 * @author juboyd
 */
public class Restito {

	private static RestitoServer server;
	private static Journal journal;
	
	public static void init(int port) {

		if (server != null) {
			server.stop();
		}

		journal = new Journal();
		server = new RestitoServer(port, journal);

		server.run();
	}
	
	public static void verify(Matcher<Request> request) {
	
		assertThat(journal, hasItem(request));
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Restito.class.getName());
}
