package com.gxp.restito.server;

import com.gxp.restito.Journal;
import java.nio.charset.Charset;
import java.util.List;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author justinboyd
 */
public class RequestJournalBuildingChannelHandler extends SimpleChannelHandler {

	private final Journal serverJournal;

	public RequestJournalBuildingChannelHandler(final Journal serverJournal) {
		this.serverJournal = serverJournal;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {

		DefaultHttpRequest request = (DefaultHttpRequest) e.getMessage();
		serverJournal.add(request);
		log.info("adding request {}:{} to journal {}", request.getMethod(), request.getUri(), serverJournal);

		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		response.setContent(ChannelBuffers.copiedBuffer("", Charset.defaultCharset()));

		e.getChannel().write(response).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				future.getChannel().close();
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		log.info(e.toString());

		Channel ch = e.getChannel();
		ch.close();
	}

	public final Journal getRequestJournal() {
		return serverJournal;
	}
	private static final Logger log = LoggerFactory.getLogger(RequestJournalBuildingChannelHandler.class.getName());
}
