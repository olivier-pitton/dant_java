package io.dant.synchro.tp.exo3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class Semaphore {

	private int permit;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	public Semaphore(int permit) {
		this.permit = permit;
	}

	public void acquire() throws InterruptedException {
		lock.lock();
		try {
			while (permit <= 0) {
				condition.await();
			}
			permit--;
		} finally {
			lock.unlock();
		}
	}

	public void release() {
		lock.lock();
		try {
			permit++;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
