package ss19.ue10.memecreator.ui;

import ss19.ue10.memecreator.model.Meme;
import ss19.ue10.memecreator.model.MemeEvent;
import ss19.ue10.memecreator.model.MemeListener;
import ss19.ue10.memecreator.model.MemeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
class TemplatePanel extends JPanel {

    private final CreationPanel creationPanel;

    TemplatePanel(MemeModel model, CreationPanel creationPanel) {
        this.creationPanel = creationPanel;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        model.getMemes().forEach(this::addMemePanel);
        // TODO: 4. MVC: attach a new MemeListener which adds a new MemePanel with the
        // meme stored in the MemeEvent
        model.addMemeListener(new MemeListener() {

            @Override
            public void memeChanged(MemeEvent e) {
                TemplatePanel.this.addMemePanel(e.getMeme());
                repaint();
            }
        });
    }

    private void addMemePanel(Meme meme) {
        MemePanel memePanel = new MemePanel(meme);
        add(memePanel);
        memePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                creationPanel.setMeme(meme);
            }
        });
        validate();
    }
}