package io.dant.network.cours.full;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Callable;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class EchoServer implements Callable<Boolean> {

	private AsynchronousServerSocketChannel server;

	@Override
	public Boolean call() throws Exception {
		server = AsynchronousServerSocketChannel.open();
		server.bind(new InetSocketAddress("localhost", 1236));
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

			@Override
			public void completed(AsynchronousSocketChannel client, Object attachment) {
				server.accept(null, this);
				try {
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					client.read(buffer).get();
					buffer.flip();
					System.out.println("J'ai reçu : " + new String(buffer.array()));
					client.write(ByteBuffer.wrap("Salut du serveur ! ".getBytes())).get();
				} catch (Exception e) {
					failed(e, null);
				}
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				exc.printStackTrace();
			}
		});
		System.in.read();
		return true;
	}

	public static void main(String[] args) throws Exception {
		new EchoServer().call();
	}

}
