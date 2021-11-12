package io.dant.synchro.cours;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class DeadLockExample {

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();

	public int lock1First() throws InterruptedException {
		System.out.println("Je lock lock1");
		synchronized (lock1) {
			// Je fais des choses ici
			Thread.sleep(1000L);
			System.out.println("Je tente de prendre lock2");
			synchronized (lock2) {
				System.out.println("lock 1 puis lock 2");
			}
		}
		return 0;
	}

	// Dead lock
	// Thread 1 --> Verrou 1 --> <code> --> Verrou 2
	// Thread 2 --> Verrou 2 --> <code> --> Verrou 1

	public int lock2First() throws InterruptedException {
		System.out.println("Je lock lock2");
		synchronized (lock2) {
			// Je fais des choses ici
			Thread.sleep(1000L);
			System.out.println("Je tente de prendre lock1");
			synchronized (lock1) {
				System.out.println("lock 2 puis lock 1");
			}
		}
		return 0;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		DeadLockExample example = new DeadLockExample();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Integer> first = executorService.submit(() -> example.lock1First());
		Future<Integer> sec = executorService.submit(() -> example.lock2First());

		first.get();
		sec.get();
		executorService.shutdown();
	}

}
