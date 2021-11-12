package io.dant.synchro.cours.atomic;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class MyCasCompteur {

	private final AtomicInteger cpt = new AtomicInteger(0);

	public int get() {
		return cpt.get();
	}

	public int incrementAndGet() {
		int current = cpt.get();
		// We try to increment until it works. When it works, we go out
		// of the loop
		while (!cpt.compareAndSet(current, current + 1)) {
			current = cpt.get();
		}
		return cpt.get();
	}

	public static void main(String[] args) throws InterruptedException {
		MyCasCompteur cpt = new MyCasCompteur();
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0 ; i < 8 ; i++) {
			completionService.submit(() -> {
				for (int j = 0 ; j < 500000 ; j++) {
					cpt.incrementAndGet();
				}
				return cpt.get();
			});
		}
		for (int i = 0 ; i < 8 ; i++) {
			completionService.take();
		}
		System.out.println(cpt.get());
		executorService.shutdown();
	}

}
