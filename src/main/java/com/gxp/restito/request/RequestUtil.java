package com.gxp.restito.request;

/**
 *
 * @author juboyd
 */
public class RequestUtil {

	public static String toString(Request r) {
		return String.format("%1$s:%2$s", r.method(), r.uri());
	}
}
