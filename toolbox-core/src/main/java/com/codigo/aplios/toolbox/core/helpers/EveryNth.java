package com.codigo.aplios.toolbox.core.helpers;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class EveryNth<C> {

	private final int nth;
	private final List<List<C>> lists = new ArrayList<>();
	private int next = 0;

	private EveryNth(int nth) {

		this.nth = nth;
		IntStream.range(0, nth)
			.forEach(i -> this.lists.add(new ArrayList<>()));
	}

	private void accept(C item) {

		this.lists.get(this.next++ % this.nth)
			.add(item);
	}

	private EveryNth<C> combine(EveryNth<C> other) {

		other.lists.forEach(l -> this.lists.get(this.next++ % this.nth)
			.addAll(l));
		this.next += other.next;
		return this;
	}

	private List<C> getResult() {

		return this.lists.get(0);
	}

	public static <T> Collector<Integer, ?, List<Integer>> collector(int nth) {

		return Collector.of(() -> new EveryNth(nth), EveryNth::accept, EveryNth::combine, EveryNth::getResult);
	}

	/**
	 * Procedura wykonuje zrzut zawartości ekranu i zapisuje go do pliku;
	 *
	 * @param winForm Okno ekranu systemu operacyjnego.
	 */
	public static final void makeWindowScreenshot(JFrame winForm) {

		// obszar roboczy okna ekranu
		// ..............................................................................................................
		final Rectangle winBound = winForm.getBounds();

		// buffor operacji graficznych
		// ..............................................................................................................
		final BufferedImage bufferedImage =
				new BufferedImage(winBound.width, winBound.height, BufferedImage.TYPE_INT_ARGB);

		winForm.paint(bufferedImage.getGraphics());

		try {
			// utworzenie pliku
			final File screenFile = File.createTempFile(winForm.getName(), ".png");

			// Use the ImageIO API to write the bufferedImage to a temporary file
			ImageIO.write(bufferedImage, "png", screenFile);
			screenFile.deleteOnExit();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static final void makeWindowsScreenshot(JFrame winForm) throws AWTException, IOException {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] screens = ge.getScreenDevices();
		Rectangle allScreenBounds = new Rectangle();
		for (GraphicsDevice screen : screens) {
			Rectangle screenBounds = screen.getDefaultConfiguration()
				.getBounds();
			allScreenBounds.width += screenBounds.width;
			allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
			allScreenBounds.x = Math.min(allScreenBounds.x, screenBounds.x);
			allScreenBounds.y = Math.min(allScreenBounds.y, screenBounds.y);
		}
		Robot robot = new Robot();
		BufferedImage bufferedImage = robot.createScreenCapture(allScreenBounds);
		File file = new File("d:\\scr.png");
		if (!file.exists())
			file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ImageIO.write(bufferedImage, "png", fos);
	}
}