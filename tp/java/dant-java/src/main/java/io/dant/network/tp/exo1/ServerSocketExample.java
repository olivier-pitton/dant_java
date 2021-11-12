package io.dant.network.tp.exo1;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
			PrintWriter out =	new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line = null;
			// Read infinitely what the client sends
			while ((line = in.readLine()) != null) {
				// Send back the line and print it
				out.println(line);
				System.out.println(line);
			}
		}
	}

}
