package swe2.ss19.ue10.demo.patterns.proxy;

public class ProxyImage implements Image {
	/** name of the image file */
	private String filename;

	/** Reference to the real image */
	private Image image;

	/**
	 * Constructor for an image proxy.
	 * 
	 * @param filename
	 *            the name of the image file.
	 */
	public ProxyImage(String filename) {
		this.filename = filename;
	}

	/**
	 * Simulates displaying the image by first creating the real image object and then delegating displaying to the real image object.
	 */
	public void displayImage() {
		if (image == null) {
			image = new RealImage(filename); // load only on demand
		}
		image.displayImage();
	}
}