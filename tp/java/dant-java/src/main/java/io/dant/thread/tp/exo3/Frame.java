package io.dant.thread.tp.exo3;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 03/12/2020
 */

public class Frame extends JFrame {

	private static final int MAX_ITERATION = 1000;
	private static final double ZOOM = 400;
	private final BufferedImage image;

	public Frame() throws HeadlessException {
		super("Mandelbrot Set");
		setBounds(100, 100, 1024, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public void draw(int x, int y, int iter) {
		image.setRGB(x, y, iter | (iter << 8));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Frame frame = new Frame();
		new SingleMandelbrot(frame, MAX_ITERATION, ZOOM).fractal();
		frame.setVisible(true);
		long end = System.currentTimeMillis() - start;
		System.out.println("Mandelbrot computation took " + end + " ms");
	}
}
