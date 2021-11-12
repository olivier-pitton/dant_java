package io.dant.thread.tp.exo1;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class Compteur extends Thread {

	private static final int MAX = 100;
	private boolean asc;

	public Compteur(String name, boolean isAsc) {
		super(name);
		this.asc = isAsc;
	}

	@Override
	public void run() {
		for (int i = 0 ; i < MAX ; i++) {
			int toPrint = (asc) ? i + 1 : MAX - i;
			System.out.println("My name is " + getName() + " and I'm counting " + toPrint);
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Compteur("T1", true);
		Thread t2 = new Compteur("T2", false);
		t1.start();
		t2.start();
	}
}
