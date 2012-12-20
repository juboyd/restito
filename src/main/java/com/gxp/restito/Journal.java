package com.gxp.restito;

import com.gxp.restito.request.Request;
import com.gxp.restito.request.NettyHttpRequestAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequest;

/**
 *
 * @author juboyd
 */
public class Journal implements Iterable<Request> {

	private final List<HttpRequest> allRequests = new ArrayList<HttpRequest>();

	public void add(DefaultHttpRequest request) {
		allRequests.add(request);
	}
	
	@Override
	public String toString() {
		return super.toString() + "{" + "allRequests=" + printRequestList() + '}';
	}
	
	private String printRequestList() {
		StringBuilder sb = new StringBuilder("[");
		for(HttpRequest request : allRequests) {
			sb.append(request.getMethod());
			sb.append(":");
			sb.append(request.getUri());
			sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	public Iterator<Request> iterator() {
		List<Request> requests = new ArrayList<Request>();
		for(HttpRequest nettyRequest : allRequests) {
			requests.add(new NettyHttpRequestAdapter(nettyRequest));
		}
		return requests.iterator();
	}
}
