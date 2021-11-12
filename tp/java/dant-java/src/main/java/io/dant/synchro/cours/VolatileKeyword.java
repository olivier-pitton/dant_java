package io.dant.synchro.cours;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 10/12/2020
 */

public class VolatileKeyword {

	/*
	 * SANS VOLATILE :
	 *  1. Thread A remarque que la variable partagée n'est pas initialisée,
	 *   il acquiert le verrou et commence l'initialisation
	 *  2. Le code généré par le compilateur a le droit de modifier la variable partagée
	 *    avant que A finisse l'initialisation (alloc mémoire, place la réf dans l'alloc, et appelle le constructeur)
	 *  3. Le thread B remarque que la variable est initialisée et retourne sa valeur sans acquérir le verrou.
	 *     Si la variable est utilisée avant que A n'ait terminé --> NullPointer
	 *
	 * Regarder double check locking sur internet pour plus d'explications
	 */

	private static volatile VolatileKeyword VAL;

	public static VolatileKeyword getInstance() {
		if (VAL == null) {
			VAL = new VolatileKeyword();
		}
		return VAL;
	}

	// Vrai singleton
	public static VolatileKeyword get() {
		return Instance.INSTANCE;
	}

	private static class Instance {

		private static final VolatileKeyword INSTANCE = new VolatileKeyword();

	}

	private VolatileKeyword() {}
}
