package swe2.ss20.exam.light;

import javax.swing.*;
import java.awt.*;

public class LightApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Light");
        Container cp = frame.getContentPane();

        LightModel model = new LightModel();
        LightPanel lightPanel = new LightPanel(model);

        cp.add(lightPanel);
        frame.pack();
        frame.setVisible(true);
    }
}