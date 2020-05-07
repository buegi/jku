package swe2.ss19.ue10.demo.patterns.proxy;

/**
 * Class for real images. Images are loaded from file. Remark: Loading is simulated by print out on System.out.
 */
public class RealImage implements Image {
	private String filename;

	/**
	 * Constructor for an image to be loaded from a file.
	 * 
	 * @param filename
	 *            the name of the image file.
	 */
	public RealImage(String filename) {
		this.filename = filename;
		loadImageFromDisk();
	}

	/**
	 * Simulates loading of a image by a printout on System.out
	 */
	private void loadImageFromDisk() {
		// Potentially expensive operation
		// ...
		System.out.println("Loading   " + filename);
	}

	/**
	 * Simulates displaying the image by a printout on System.out.
	 */
	public void displayImage() {
		System.out.println("Displaying " + filename);
	}
}