package io.dant.network.tp.exo4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class ServerCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

	private final AsynchronousSocketChannel socketChannel;

	public ServerCompletionHandler(AsynchronousSocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] array = attachment.array();
		System.out.println(new String(array));
		ByteBuffer sendBuffer = ByteBuffer.wrap(array);
		attachment.clear();
		socketChannel.write(sendBuffer, sendBuffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				attachment.clear();
				ByteBuffer readData = ByteBuffer.allocate(1024);
				socketChannel.read(readData, readData, new ServerCompletionHandler(socketChannel));
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
				attachment.clear();
				try {
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		exc.printStackTrace();
		attachment.clear();
		try {
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
