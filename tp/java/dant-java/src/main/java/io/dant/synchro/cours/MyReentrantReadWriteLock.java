package io.dant.synchro.cours;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class MyReentrantReadWriteLock {

	private double amount = 100.0;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();

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
			return amount;
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Opération d'écriture à verrouiller
	public double takeMoney(double val) {
		lock.writeLock().lock();
		try {
			amount -= val;
			return amount;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public static void main(String[] args) {
		MyReentrantReadWriteLock account = new MyReentrantReadWriteLock();
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
				double val = account.takeMoney(20);
				System.out.println("I have taken 20, I have " + val + " euros");
				Thread.sleep(500);
			}
		});
	}

}
