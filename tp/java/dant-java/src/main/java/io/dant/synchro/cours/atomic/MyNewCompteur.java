package io.dant.synchro.cours.atomic;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class MyNewCompteur {

	private AtomicInteger cpt = new AtomicInteger(0);

	public int get() {
		return cpt.get();
	}

	public int incrementAndGet() {
		return cpt.incrementAndGet();
	}

	public static void main(String[] args) throws InterruptedException {
		MyNewCompteur cpt = new MyNewCompteur();
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0 ; i < 8 ; i++) {
			completionService.submit(() -> {
				for (int j = 0 ; j < 5000000 ; j++) {
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
