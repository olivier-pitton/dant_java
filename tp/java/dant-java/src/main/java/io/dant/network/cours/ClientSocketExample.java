package io.dant.network.cours;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class ClientSocketExample {

	public static void main(String[] args) throws IOException {
		try(Socket socket = new Socket("localhost", 1236)) {
			socket.getOutputStream().write("salut".getBytes("UTF-8"));
		}
	}
}
