package com.gxp.restito.request;

import org.jboss.netty.handler.codec.http.HttpRequest;

/**
 *
 * @author juboyd
 */
public class NettyHttpRequestAdapter implements Request {

	private final HttpRequest nettyRequest;

	public NettyHttpRequestAdapter(HttpRequest nettyRequest) {
		this.nettyRequest = nettyRequest;
	}
	
	public String method() {
		return nettyRequest.getMethod().toString();
	}

	public String uri() {
		return nettyRequest.getUri();
	}
	
}
