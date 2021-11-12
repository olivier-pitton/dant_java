package io.dant.synchro.cours.atomic;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class MyOldCompteur {

	private int cpt = 0;

	public int get() {
		return cpt;
	}

	public int incrementAndGet() {
		return ++cpt;
	}

	public static void main(String[] args) throws InterruptedException {
		MyOldCompteur cpt = new MyOldCompteur();
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0 ; i < 8 ; i++) {
			completionService.submit(() -> {
				for (int j = 0 ; j < 500_000 ; j++) {
					cpt.incrementAndGet();
				}
				return cpt.get();
			});
		}
		for (int i = 0 ; i < 8 ; i++) {
			completionService.take();
		}
		// Un résultat faux à cause du multi-thread est une race condition
		System.out.println(cpt.get());
		executorService.shutdown();
	}

}
