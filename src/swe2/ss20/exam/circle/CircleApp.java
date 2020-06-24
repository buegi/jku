package swe2.ss20.exam.circle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CircleApp {
    private CircleModel circleModel;
    private JSlider radiusSlider;
    private CirclePainter circlePainter;

    // Implementierung des Listeners, der beim Verschieben des Sliders durch
    // den Benutzer das Datenmodell aktualisiert

    private ChangeListener sliderChangedListener = new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            int newRadius = radiusSlider.getValue();
            circleModel.setRadius(newRadius);
        }
    };

    // Hauptmethode, die das Fenster erzeugt und anzeigt
    public void start() {
        // Anlegen des Datenmodells
        circleModel = new CircleModel();

        // Erzeugen des JSlider zum Einstellen des Radius
        radiusSlider = new JSlider(0, 400, circleModel.getRadius());

        // Erzeugen der graphischen View für den Kreis
        circlePainter = new CirclePainter(circleModel);

        // ChangeListener für den JSlider
        radiusSlider.addChangeListener(sliderChangedListener);

        // Hauptfensters erstellen und anzeigen
        JFrame frame = new JFrame("Circle");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(radiusSlider, BorderLayout.NORTH);
        frame.getContentPane().add(circlePainter, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CircleApp().start();
    }
}