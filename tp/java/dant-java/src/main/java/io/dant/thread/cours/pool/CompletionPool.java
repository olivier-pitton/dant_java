package io.dant.thread.cours.pool;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class CompletionPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CompletionService<Long> completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0 ; i < 10 ; i++) {
			final long timeToWait = (i + 1) * 1000;

			completionService.submit(() -> {
				long start = System.currentTimeMillis();
				Thread.sleep(timeToWait);
				long id = timeToWait / 1000;
				System.out.println("Je suis exécuté " + id + " en " + (System.currentTimeMillis() - start) + " ms");
				return id;
			});
		}
		// Les résultats arrivent au fil de l'eau
		for (int i = 0 ; i < 10 ; i++) {
			Long myResult = completionService.take().get();
			System.out.println("Resultat : " + myResult);
		}
		executorService.shutdown();
	}

}
