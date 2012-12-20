package com.gxp.restito.server;

import com.gxp.restito.Journal;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Standalone ad server
 */
public class RestitoServer {

	private static final String HOST = "localhost";
	private final ExecutorService bossExecutor = Executors.newCachedThreadPool();
	private final ExecutorService workerExecutor = Executors.newCachedThreadPool();
	private final ChannelFactory factory =
		new NioServerSocketChannelFactory(
		bossExecutor,
		workerExecutor);
	private final ServerBootstrap bootstrap = new ServerBootstrap(factory);
	private final int port;

	public RestitoServer(final int port, final Journal journal) {

		this.port = port;
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() {

				return Channels.pipeline(
					new HttpServerCodec(),
					new RequestJournalBuildingChannelHandler(journal));
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
	}

	public void run() {

		bootstrap.bind(new InetSocketAddress(HOST, port));
	}

	public void stop() {
		try {
			bossExecutor.shutdown();
			workerExecutor.shutdown();
			bossExecutor.awaitTermination(100, TimeUnit.MILLISECONDS);
			workerExecutor.awaitTermination(100, TimeUnit.MILLISECONDS);
			bootstrap.releaseExternalResources();
		} catch (InterruptedException ex) {
			throw new RuntimeException("failed to cleanly stop server", ex);
		}
	}
	private static final Logger logger = LoggerFactory.getLogger(RestitoServer.class.getName());
}