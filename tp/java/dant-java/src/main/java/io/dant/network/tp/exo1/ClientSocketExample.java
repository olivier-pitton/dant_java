package io.dant.network.tp.exo1;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class ClientSocketExample {

	public static void main(String[] args) throws IOException {
		try(Socket socket = new Socket("localhost", 1236);
		    Scanner scanner = new Scanner(System.in);
		    PrintWriter out =	new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			while (scanner.hasNext()) {
				// Read the line from stdin
				String line = scanner.next();
				// Send the line to the server
				out.println(line);
				// Print what the server is sending
				System.out.println(in.readLine());
			}
		}
	}

}
