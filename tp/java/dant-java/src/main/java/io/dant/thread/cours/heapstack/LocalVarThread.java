package io.dant.thread.cours.heapstack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class LocalVarThread extends Thread {

	private int cpt = 0;
	private List<Integer> list = new ArrayList<>();

	public void run() {
		cpt++;
		list.add(cpt);
		System.out.println(cpt + " " + list);
	}

	// Les variables ne sont pas partagées par les threads
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new LocalVarThread();
		Thread t2 = new LocalVarThread();
		t1.start();
		t2.start();
	}

}
