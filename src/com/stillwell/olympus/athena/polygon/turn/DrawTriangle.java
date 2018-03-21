package com.stillwell.olympus.athena.polygon.turn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DrawTriangle extends JFrame
{
	
	private static final long serialVersionUID = 1L;

    private int[] xPoints = new int[]{0, 25, 50};
    private int[] yPoints = new int[]{50, 0, 50};

    double angle = 0f;

    public DrawTriangle() {
        super("Dimitry Rakhlei");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawTrianglePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawTrianglePanel extends JPanel implements MouseListener,
                    MouseMotionListener {

    	private static final long serialVersionUID = 1L;
        private Point clickPoint;

        public DrawTrianglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
            clickPoint = new Point(100, 100);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        protected Dimension getTriangleSize() {

            int maxX = 0;
            int maxY = 0;
            for (int index = 0; index < xPoints.length; index++) {
                maxX = Math.max(maxX, xPoints[index]);
            }
            for (int index = 0; index < yPoints.length; index++) {
                maxY = Math.max(maxY, yPoints[index]);
            }
            return new Dimension(maxX, maxY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            AffineTransform at = new AffineTransform();
            Dimension size = getTriangleSize();
            int x = clickPoint.x - (size.width / 2);
            int y = clickPoint.y - (size.height / 2);
            at.translate(x, y);
            at.rotate(Math.toRadians(angle), clickPoint.x - x, clickPoint.y - y);
            g2d.setTransform(at);
            g2d.drawPolygon(xPoints, yPoints, 3);
            // Guide
            g2d.setColor(Color.RED);
            g2d.drawLine(size.width / 2, 0, size.width / 2, size.height / 2);
            g2d.dispose();

        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed called");
            //          clickPoint = e.getPoint();
            repaint();

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released called");
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged called");

            angle = -Math.toDegrees(Math.atan2(e.getPoint().x - clickPoint.x, e.getPoint().y - clickPoint.y)) + 180;

            repaint();
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse Entered.");
        }

        public void mouseExited(MouseEvent e) {
            System.out.println("Mouse exited.");
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                new DrawTriangle();
            }
        });
    }

}