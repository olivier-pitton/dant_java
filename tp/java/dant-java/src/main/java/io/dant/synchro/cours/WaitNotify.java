package io.dant.synchro.cours;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class WaitNotify {

	private double amount = 100.0;

	// Opération de lecture à verrouiller
	public synchronized double getMoney() {
		return amount;
	}

	// Opération d'écriture à verrouiller
	public synchronized double addMoney(double val) {
		amount += val;
		this.notifyAll();
		return amount;
	}

	// Opération d'écriture à verrouiller
	public synchronized double takeMoney(double val) {
		while (amount - val < 0.0) {
			try {
				System.err.println("I can't take " + val + " because I have " + amount);
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		amount -= val;
		return amount;
	}

	public static void main(String[] args) {
		WaitNotify account = new WaitNotify();
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
