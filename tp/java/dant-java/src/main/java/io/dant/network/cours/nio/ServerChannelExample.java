package io.dant.network.cours.nio;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class ServerChannelExample {

	public static void main(String[] args) throws IOException {
		System.out.println("Creating a server on port 1236");
		try(ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
			serverSocket.bind(new InetSocketAddress(1236));
			System.out.println("Waiting a connection...");
			SocketChannel socketChannel = serverSocket.accept();
			System.out.println("A client is connected from " + socketChannel.socket().getInetAddress().getHostAddress());
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			socketChannel.read(buffer);
			buffer.flip();
			System.out.println("Client has sent : \n" + new String(buffer.array()));
		}
	}

}
