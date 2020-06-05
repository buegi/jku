package swe2.ss20.ue11.graphiceditor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import swe2.ss20.ue11.graphiceditor.model.GraphicModel;
import swe2.ss20.ue11.graphiceditor.view.GraphicFrame;

/**
 * Main class for starting the application.
 */
public class Main {

    public static void main(final String[] args) {

        final GraphicModel model = new GraphicModel();

        // TODO: create prototypes, factories etc. needed to initialize application
        final JFrame frame = new GraphicFrame(model);
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();
        });
    }
}