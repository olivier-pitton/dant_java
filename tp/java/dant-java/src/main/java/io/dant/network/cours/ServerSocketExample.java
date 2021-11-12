package io.dant.network.cours;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class ServerSocketExample {

	public static void main(String[] args) throws IOException {
		System.out.println("Creating a server on port 1236");
		try(ServerSocket serverSocket = new ServerSocket(1236)) {
			System.out.println("Waiting a connection...");
			Socket socket = serverSocket.accept();
			System.out.println("A client is connected from " + socket.getInetAddress().getHostAddress());
			StringWriter writer = new StringWriter();
			IOUtils.copy(socket.getInputStream(), writer, StandardCharsets.UTF_8);
			System.out.println("Client has sent : \n" + writer);
		}
	}

}
