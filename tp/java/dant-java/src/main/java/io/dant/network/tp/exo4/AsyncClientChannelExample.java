package io.dant.network.tp.exo4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

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
				socket.write(ByteBuffer.wrap(line.getBytes(StandardCharsets.UTF_8)), null, new CompletionHandler<Integer, Object>() {
					@Override
					public void completed(Integer result, Object attachment) {
						ByteBuffer readData = ByteBuffer.allocate(1024);
						socket.read(readData, null, new CompletionHandler<Integer, Object>() {
							@Override
							public void completed(Integer result, Object attachment) {
								readData.flip();
								System.out.println(new String(readData.array()));
								readData.clear();
							}

							@Override
							public void failed(Throwable exc, Object attachment) {
								exc.printStackTrace();
							}
						});
					}

					@Override
					public void failed(Throwable exc, Object attachment) {
						exc.printStackTrace();
					}
				});

			}
		}
	}

}
