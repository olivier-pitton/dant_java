package io.dant.synchro.tp.exo2;

import io.dant.synchro.cours.atomic.MyOldCompteur;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class TestSpinLock {

	private static int val;
	private static final SpinLock lock = new SpinLock();

	public static void increment() {
		lock.lock();
		try {
			val++;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0 ; i < 8 ; i++) {
			completionService.submit(() -> {
				for (int j = 0 ; j < 500000 ; j++) {
					increment();
				}
				return val;
			});
		}
		for (int i = 0 ; i < 8 ; i++) {
			completionService.take();
		}
		// Un résultat faux à cause du multi-thread est une race condition
		System.out.println(val);
		executorService.shutdown();
	}

}
