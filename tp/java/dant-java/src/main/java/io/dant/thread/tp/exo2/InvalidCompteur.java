package io.dant.thread.tp.exo2;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class InvalidCompteur {

	private static int CPT = 0;

	public static void main(String[] args) throws InterruptedException {
		Runnable run = (() -> {
			for (int i = 0 ; i < 1_000_000_000 ; i++) {
				CPT++;
			}
		});
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(CPT);
	}

}
