package io.dant.synchro.tp.exo3;

import io.dant.synchro.tp.exo2.SpinLock;
import io.dant.synchro.tp.exo2.TestSpinLock;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class TestSemaphore {

	private static int val;
	private static final Semaphore lock = new Semaphore(1);

	public static void increment() throws InterruptedException {
		lock.acquire();
		try {
			val++;
		} finally {
			lock.release();
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
