package io.dant.thread.tp.exo3;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 04/12/2020
 */

public class SingleMandelbrot {

	protected final Frame frame;
	protected final int maxIteration;
	protected final double zoom;

	public SingleMandelbrot(Frame frame, int maxIteration, double zoom) {
		this.frame = frame;
		this.maxIteration = maxIteration;
		this.zoom = zoom;
	}

	public void fractal() {
		for (int y = 0; y < frame.getHeight(); y++) {
			drawOnX(y);
		}
	}

	protected void drawOnX(int y) {
		for (int x = 0; x < frame.getWidth(); x++) {
			double zx = 0;
			double zy = 0;
			double cX = (x - 400) / zoom;
			double cY = (y - 300) / zoom;
			int iter = maxIteration;
			while (zx * zx + zy * zy < 4 && iter > 0) {
				double tmp = zx * zx - zy * zy + cX;
				zy = 2.0 * zx * zy + cY;
				zx = tmp;
				iter--;
			}
			frame.draw(x, y, iter);
		}
	}

}
