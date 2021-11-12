package io.dant.synchro.cours;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class MyCondition {

	private double amount = 100.0;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Condition condition = lock.writeLock().newCondition();

	// Opération de lecture à verrouiller
	public double getMoney() {
		lock.readLock().lock();
		try {
			return amount;
		} finally {
			lock.readLock().unlock();
		}
	}

	// Opération d'écriture à verrouiller
	public double addMoney(double val) {
		lock.writeLock().lock();
		try {
			amount += val;
			condition.signalAll();
			return amount;
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Opération d'écriture à verrouiller
	public double takeMoney(double val) {
		lock.writeLock().lock();
		try {
			while (amount - val < 0.0) {
				System.err.println("I can't take " + val + " because I have " + amount);
				condition.await();
			}
			amount -= val;
			return amount;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return amount;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public static void main(String[] args) {
		MyCondition account = new MyCondition();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(() -> {
			while(true) {
				double val = account.addMoney(20);
				System.out.println("I have added 20, I have " + val + " euros");
				Thread.sleep(500);
			}
		});

		executorService.submit(() -> {
			while(true) {
				double val = account.takeMoney(35);
				System.out.println("I have taken 35, I have " + val + " euros");
				Thread.sleep(500);
			}
		});
	}

}
