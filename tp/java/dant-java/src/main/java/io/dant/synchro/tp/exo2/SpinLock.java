package io.dant.synchro.tp.exo2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class SpinLock {

	private final AtomicBoolean lock = new AtomicBoolean(false);

	public void lock() {
		while (!lock.compareAndSet(false, true)) {
		}
	}

	public void unlock() {
		lock.compareAndSet(true, false);
	}
}
