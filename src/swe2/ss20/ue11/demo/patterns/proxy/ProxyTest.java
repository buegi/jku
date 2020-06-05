package swe2.ss20.ue11.demo.patterns.proxy;

/**
 * Example which simulates lazy loading of images. Images are loaded from file when they are displayed for the first time.
 */
class ProxyTest {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Image image1 = new ProxyImage("HiRes_10MB_Photo1");
        Image image2 = new ProxyImage("HiRes_10MB_Photo2");
        Image image3 = new ProxyImage("HiRes_10MB_Photo3");

        image1.displayImage(); // loading necessary
        image2.displayImage(); // loading necessary
        image2.displayImage(); // no loading necessary; already done
        // image3 is never displayed so it is not loaded at all
    }
}