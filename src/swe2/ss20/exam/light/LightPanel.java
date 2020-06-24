package swe2.ss20.exam.light;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LightPanel extends JPanel {
    private final LightModel model;

    public LightPanel(LightModel model) {
        this.setBackground(Color.GRAY);
        this.model = model;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /* Licht einschalten */
                model.switchLight();
            }
        });
        /* Horchen auf Ereignisse beim Model um Neuzeichnen anzustossen */
        model.addLightListener(e -> repaint());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* Zeichnen des gelben Balls, wenn Lich an */
        if (model.isOn()) {
            g.setColor(Color.YELLOW);
            g.fillOval(50, 40, 200, 200);
        }
        g.setColor(Color.DARK_GRAY);
        g.drawOval(50, 50, 200, 200);
    }

}