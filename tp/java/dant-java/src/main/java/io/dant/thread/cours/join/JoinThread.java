package io.dant.thread.cours.join;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class JoinThread extends Thread implements Cloneable {

	public JoinThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0 ; i < 5 ; i++) {
			try {
				Thread.sleep(200);
				System.out.println("I'm " + getName() + " waiting since " + ((i + 1) * 2) + " sec");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new JoinThread("First thread");
		Thread t2 = new JoinThread("Second thread");
		t1.start();
		t2.start();

		t2.join();
		t1.join();
	}
}
