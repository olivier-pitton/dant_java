package io.dant.thread.cours.creation;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class LambdaThread {

	public static void main(String... args) {
		Thread t = new Thread(() -> System.out.println("With a lambda !"));
		t.start();
	}

}
