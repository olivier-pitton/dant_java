package io.dant.network.cours.full;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class EchoClient {

	public static void main(String[] args) throws Exception {
		try(AsynchronousSocketChannel socket = AsynchronousSocketChannel.open()) {
			socket.connect(new InetSocketAddress("localhost", 1236)).get();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put("salut".getBytes("UTF-8"));
			socket.write(buffer).get();

			buffer.clear();
			socket.read(buffer).get();
			buffer.flip();
			System.out.println("Server said " + new String(buffer.array()));
		}
	}

}
