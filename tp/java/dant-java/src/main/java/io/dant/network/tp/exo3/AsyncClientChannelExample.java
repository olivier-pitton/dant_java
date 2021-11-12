package io.dant.network.tp.exo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class AsyncClientChannelExample {

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		try(AsynchronousSocketChannel socket = AsynchronousSocketChannel.open();
		    Scanner scanner = new Scanner(System.in)) {
			socket.connect(new InetSocketAddress("localhost", 1236)).get();

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				socket.write(ByteBuffer.wrap(line.getBytes(StandardCharsets.UTF_8))).get();
				ByteBuffer readData = ByteBuffer.allocate(1024);
				socket.read(readData).get();
				readData.flip();
				System.out.println(new String(readData.array()));
				readData.clear();
			}
		}
	}

}
