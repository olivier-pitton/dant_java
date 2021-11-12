package io.dant.thread.cours.metadata;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class MetadataThread extends Thread {

	@Override
	public void run() {
		printThreadInfo(this);
	}

	public static void main(String[] args) {
		new MetadataThread().start();
		//printThreadInfo(Thread.currentThread());
	}

	public static void printThreadInfo(Thread t) {
		System.out.println("My id " + t.getId());
		System.out.println("My name " + t.getName());
		System.out.println("Am I a daemon ? " + t.isDaemon());
		System.out.println("Am I alive ? " + t.isAlive());
		System.out.println("My status is " + t.getState());
		System.out.println("My priority is " + t.getPriority());

	}
}
