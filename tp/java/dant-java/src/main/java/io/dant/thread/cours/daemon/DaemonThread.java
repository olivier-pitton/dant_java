package io.dant.thread.cours.daemon;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class DaemonThread {

	public static void main(String... args) throws InterruptedException {
		Thread daemonThread = new Thread(() -> {
			try {
				while (true) {
					System.out.println("Execution demon");
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "Demon");

		daemonThread.setDaemon(true);
		daemonThread.start();
	}

}
