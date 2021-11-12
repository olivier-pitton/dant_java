package io.dant.thread.cours.heapstack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class ShareVarThread {

	private static int GLOBAL_CPT = 0;
	private static List<Integer> GLOBAL_LIST = new ArrayList<>();

	// Les variables sont partagées par les thread
	public static void main(String[] args) {
		Runnable run = (() -> {
			GLOBAL_CPT++;
			GLOBAL_LIST.add(GLOBAL_CPT);
			System.out.println(GLOBAL_CPT + " " + GLOBAL_LIST);
		});

		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		t1.start();
		t2.start();
	}
}
