package io.dant.network.cours.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class ClientChannelExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		try(SocketChannel socket = SocketChannel.open(new InetSocketAddress("localhost", 1236))) {
			ByteBuffer buffer = ByteBuffer.wrap("salut".getBytes("UTF-8"));
			socket.write(buffer);
		}
	}

}
