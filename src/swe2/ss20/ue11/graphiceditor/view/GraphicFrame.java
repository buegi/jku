package swe2.ss20.ue11.graphiceditor.view;

import swe2.ss20.ue11.graphiceditor.decorator.NumberDecorator;
import swe2.ss20.ue11.graphiceditor.model.GraphicModel;
import swe2.ss20.ue11.graphiceditor.objects.Circle;
import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;
import swe2.ss20.ue11.graphiceditor.objects.Rectangle;
import swe2.ss20.ue11.graphiceditor.objects.Star;
import swe2.ss20.ue11.graphiceditor.visitor.BackwardVisitor;
import swe2.ss20.ue11.graphiceditor.visitor.ForwardVisitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;


/**
 * Frame class for the graphical objects.
 */
public class GraphicFrame extends JFrame {

    private static final long serialVersionUID = -2329212433155597042L;
    private final GraphicModel model;
    private final GraphicView view;
    private GraphicObject selectedPrototype;

    /**
     * Constructs the frame with the model.
     *
     * @param model the graphical model
     */
    public GraphicFrame(GraphicModel model) {
        super("Graphic Editor");

        this.model = model;

        this.getContentPane().setLayout(new BorderLayout());
        view = new GraphicView();
        this.getContentPane().add(view, BorderLayout.CENTER);
        view.setBackground(Color.WHITE);

        final JToolBar toolBar = new JToolBar();
        this.getContentPane().add(toolBar, BorderLayout.PAGE_START);

        // TODO Task 2: Create buttons that are based on a prototype graphic object.
        //              Implement ActionListener to set selected prototype object.

        JToggleButton rectBtn = new JToggleButton("Rectangle");
        JToggleButton circleBtn = new JToggleButton("Circle");
        JToggleButton starBtn = new JToggleButton("Star");

        ButtonGroup btnGroup = new ButtonGroup();

        toolBar.add(rectBtn);
        toolBar.add(circleBtn);
        toolBar.add(starBtn);

        btnGroup.add(rectBtn);
        btnGroup.add(circleBtn);
        btnGroup.add(starBtn);

        rectBtn.addActionListener(view);
        circleBtn.addActionListener(view);
        starBtn.addActionListener(view);

    }

    /**
     * Generates a random color
     *
     * @return a randomly generated color
     */
    private static Color getRandomColor() {
        int x = (int) (Math.random() * Integer.MAX_VALUE);
        return new Color(x, false);
    }

    /**
     * The view class for drawing the graphical objects.
     */
    @SuppressWarnings("serial")
    private class GraphicView extends JComponent implements ActionListener {

        private int numShape;

        /**
         * Constructor for the graphical view.
         * Adds listener for mouse events and key events and a
         * listener to the model for repainting the view upon model changes.
         */
        public GraphicView() {
            super();
            setPreferredSize(new Dimension(600, 400));

            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    requestFocusInWindow();

                    int x = e.getX();
                    int y = e.getY();
                    GraphicObject graphicObject = null;

                    // TODO Task 2: Clone prototype to create a new graphicObject
                    if (numShape == 1) {
                        graphicObject = new Rectangle(x, y, 60, 40);
                    } else if (numShape == 2) {
                        graphicObject = new Circle(x, y, 20);
                    } else if (numShape == 3) {
                        graphicObject = new Star(x, y);
                    }


                    if (graphicObject == null) {
                        return;
                    }
                    // TODO Task 3: Create decorated object
                    graphicObject = new NumberDecorator(graphicObject, 1 + model.getGraphicObjects().size());
                    graphicObject.setX(x);
                    graphicObject.setY(y);

                    graphicObject.setColor(getRandomColor());
                    model.add(graphicObject);
                }
            });

            this.addKeyListener(new KeyAdapter() {

                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        // TODO Task 4: Apply forwardVisitor to model
                        final ForwardVisitor fv = new ForwardVisitor();
                        for (GraphicObject go : model.getGraphicObjects()) {
                            go.accept(fv);
                        }
                        repaint();

                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        // TODO Task 4: Apply backwardVisitor to model
                        final BackwardVisitor bv = new BackwardVisitor();
                        for (GraphicObject go : model.getGraphicObjects()) {
                            go.accept(bv);
                        }
                        repaint();
                    }
                }
            });
            model.addGraphicChangedListener(ce -> {
                repaint();
            });
        }

        /**
         * Paints the graphical object of the model.
         *
         * @param g the graphics context
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (GraphicObject o : model.getGraphicObjects()) {
                g.setColor(o.getColor());
                o.paint(g);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JToggleButton actualBtn = (JToggleButton) e.getSource();

            if (actualBtn.getText() == "Rectangle") {
                this.numShape = 1;
            } else if (actualBtn.getText() == "Circle") {
                this.numShape = 2;
            } else if (actualBtn.getText() == "Star") {
                this.numShape = 3;
            }
        }
    }
}