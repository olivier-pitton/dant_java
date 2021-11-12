package io.dant.network.cours.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class AsyncClientChannelExample {

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		try(AsynchronousSocketChannel socket = AsynchronousSocketChannel.open()) {
			socket.connect(new InetSocketAddress("localhost", 1236)).get();
			ByteBuffer buffer = ByteBuffer.wrap("salut".getBytes("UTF-8"));
			Future<Integer> future = socket.write(buffer);
			future.get();
		}
	}

}
