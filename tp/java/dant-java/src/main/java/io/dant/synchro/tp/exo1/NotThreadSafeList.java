package io.dant.synchro.tp.exo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class NotThreadSafeList {

	private static final int MAX_SIZE = 10;
	private final List<String> arr = new ArrayList<>();
	private int current;

	public void add(String val) {
		arr.add(val);
		current++;
	}

	public String poll() {
		String val = arr.remove(0);
		current--;
		return val;
	}

	public String take() {
		String val = arr.remove(0);
		current--;
		return val;
	}
}
