package com.pacman.utils;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This class holds a {@link BufferedImage}, when the {@link BufferedImage} is
 * Serialized it is saved as a multi-dimensional Array of ARBG pixel integers.
 * <p>
 * The <code>get()</code> method returns the {@link BufferedImage}, if it has
 * been Serialized, it re-creates the {@link BufferedImage} from the pixel data
 * Array and then returns it.
 * 
 * @author Sebastian Troy
 * 
 */
public class SerializableBufferedImage implements Serializable {
	private static final long serialVersionUID = 1L;

	private transient BufferedImage image = null;

	private int imageWidth;
	private int imageHeight;
	private int[][] pixelArray;

	/**
	 * @param image
	 *            - the {@link BufferedImage} which needs to be
	 *            {@link Serializable}
	 */
	public SerializableBufferedImage(BufferedImage image) {
		set(image);
	}

	/**
	 * @param image
	 *            - the {@link BufferedImage} you wish this class to represent.
	 */
	public void set(BufferedImage image) {
		this.image = image;

		imageWidth = image.getWidth();
		imageHeight = image.getHeight();

		pixelArray = new int[imageWidth][imageHeight];

		setPixelArray();
	}

	/**
	 * If the {@link BufferedImage} is null, it re-loads the image from the
	 * stored pixel data in <code>pixelArray</code>.
	 * <p>
	 * It always returns the {@link BufferedImage} stored in this class.
	 * 
	 * @return the {@link BufferedImage} stored in the instance of this class.
	 */
	public BufferedImage get() {
		if (image == null) {
			image = new BufferedImage(imageWidth, imageHeight,
					BufferedImage.TYPE_INT_ARGB);
			for (int x = 0; x < imageWidth; x++)
				for (int y = 0; y < imageHeight; y++)
					image.setRGB(x, y, pixelArray[x][y]);
		}
		return image;
	}

	private void setPixelArray() {
		for (int x = 0; x < imageWidth; x++)
			for (int y = 0; y < imageHeight; y++)
				pixelArray[x][y] = image.getRGB(x, y);
	}

}
