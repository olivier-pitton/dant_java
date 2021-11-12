package io.dant.thread.cours.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class ManyThreadPool {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Callable<Long>> callables = new ArrayList<>();
		for (int i = 0 ; i < 10 ; i++) {
			final long timeToWait = (i + 1) * 1000;
			callables.add(() -> {
				long start = System.currentTimeMillis();
				Thread.sleep(timeToWait);
				long id = timeToWait / 1000;
				System.out.println("Je suis exécuté " + id + " en " + (System.currentTimeMillis() - start) + " ms");
				return id;
			});
		}
		// Les 10 threads sont invoqués en parallèle
		// On attend que les 10 soient finies
		List<Future<Long>> futures = executorService.invokeAll(callables);
		for (Future<Long> future : futures) {
			long myResult = future.get();
			System.out.println("Resultat : " + myResult);
		}
		executorService.shutdown();
	}

}
