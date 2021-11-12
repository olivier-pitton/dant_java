package io.dant.thread.cours.creation;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class InheritanceThread extends Thread {

	@Override
	public void run() {
		System.out.println("My extends thread");
	}

	public static void main(String... args) {
		InheritanceThread t = new InheritanceThread();
		t.start();
	}

}
