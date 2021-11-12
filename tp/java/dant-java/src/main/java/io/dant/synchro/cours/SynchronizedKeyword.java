package io.dant.synchro.cours;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class SynchronizedKeyword {

	private List<String> data = new ArrayList<>();

	// Synchronized sur une méthode = synchronized(this) {} sur toute la méthode
	public synchronized void add(String val) {
		data.add(val);
	}

	public synchronized String get(int pos) {
		return data.get(pos);
	}

	public void add2(String val) {
		synchronized (data) {
			data.add(val);
		}
	}

	public synchronized String get2(int pos) {
		synchronized (data) {
			return data.get(pos);
		}
	}

}
