package io.dant.thread.cours.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class CallablePool {

	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		try {
			Future<Integer> future = executorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(3000L);
					return 5;
				}
			});
			int myResult = future.get(2, TimeUnit.SECONDS);
			System.out.println("Resultat : " + myResult);
		} finally {
			executorService.shutdown();
		}
	}


}
