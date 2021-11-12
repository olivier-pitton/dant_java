package io.dant.network.tp.exo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class AsyncServerChannelExample {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		System.out.println("Creating a server on port 1236");
		try(AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open()) {
			serverSocket.bind(new InetSocketAddress(1236));
			System.out.println("Waiting a connection...");
			Future<AsynchronousSocketChannel> future = serverSocket.accept();
			AsynchronousSocketChannel socketChannel = future.get();
			System.out.println("A client is connected from " + socketChannel.getRemoteAddress());
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (true) {
				socketChannel.read(buffer).get();
				buffer.flip();
				System.out.println(new String(buffer.array()));
				socketChannel.write(ByteBuffer.wrap(buffer.array())).get();
				buffer.clear();
			}
		}
	}

}
