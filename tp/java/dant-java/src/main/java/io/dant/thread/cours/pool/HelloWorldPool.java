package io.dant.thread.cours.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class HelloWorldPool {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<?> future = executorService.submit(() -> System.out.println("My Runnable"));
		future.get();
		executorService.shutdown();
	}

}
