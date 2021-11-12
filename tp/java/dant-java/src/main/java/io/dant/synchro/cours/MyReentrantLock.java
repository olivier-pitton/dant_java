package io.dant.synchro.cours;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class MyReentrantLock {

	private double amount = 100.0;
	private final Lock lock = new ReentrantLock();

	// Opération de lecture à verrouiller
	public double getMoney() {
		lock.lock();
		try {
			return amount;
		} finally {
			lock.unlock();
		}
	}

	// Opération d'écriture à verrouiller
	public double addMoney(double val) {
		lock.lock();
		try {
			amount += val;
			return amount;
		} finally {
			lock.unlock();
		}
	}

	// Opération d'écriture à verrouiller
	public double takeMoney(double val) {
		lock.lock();
		try {
			amount -= val;
			return amount;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		MyReentrantLock account = new MyReentrantLock();
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
