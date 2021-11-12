package io.dant.network.tp.exo4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class AsyncServerChannelExample {

	public static void main(String[] args) throws IOException {
		System.out.println("Creating a server on port 1236");
		try(AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open()) {
			serverSocket.bind(new InetSocketAddress(1236));
			System.out.println("Waiting a connection...");

			while (true) {
				serverSocket.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

					@Override
					public void completed(AsynchronousSocketChannel result, Object attachment) {
						serverSocket.accept(null, this);
						System.out.println("A client is connected");
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						result.read(buffer, buffer, new ServerCompletionHandler(result));
					}

					@Override
					public void failed(Throwable exc, Object attachment) {
						exc.printStackTrace();
					}
				});
				System.in.read();
			}
		}
	}

}
