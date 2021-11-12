package io.dant.thread.cours.creation;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("My Runnable");
	}

	public static void main(String... args) {
		Thread t = new Thread(new MyRunnable());
		t.start();
	}
}
