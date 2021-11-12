package io.dant.synchro.tp.exo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class SynchronizedListCorrection {

	private static final int MAX_SIZE = 10;
	private final Object lockAdd = new Object();
	private final Object lockTake = new Object();
	private final List<String> arr = new ArrayList<>();
	private volatile int current;

	public void add(String val) throws InterruptedException {
		// Block the lockAdd so we don't have a take in parallel
		// and we can wait if list is empty
		synchronized (lockAdd) {
			while (current + 1 > MAX_SIZE) {
				lockAdd.wait();
			}
		}
		synchronized (lockTake) {
			arr.add(val);
			current++;
			lockTake.notify();
		}
	}

	public String poll() {
		// Ensure there is no add (for instance if we have 3 element, there is no wait)
		synchronized (lockAdd) {
			if (current == 0) {
				return null;
			}
			String val = arr.remove(0);
			current--;
			lockAdd.notify();
			return val;
		}
	}

	public String take() throws InterruptedException {
		// Block the lockTake to ensure we have 1 element
		// the add will wakes up this thread
		synchronized (lockTake) {
			while (current == 0) {
				lockTake.wait();
			}
		}
		// Ensure there is no add (for instance if we have 3 element, there is no wait)
		synchronized (lockAdd) {
			String val = arr.remove(0);
			current--;
			lockAdd.notify();
			return val;
		}
	}
}
