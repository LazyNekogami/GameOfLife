package GameUI;

import Mechanics.Field;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends java.awt.Canvas implements MouseListener {
    // Bounds
    protected int width = 495;
    protected int n = 13;

    protected Field field;

    Canvas() {
        super();
        addMouseListener(this);
        field = new Field(n, width);
    }

    Canvas(int height) {
        super();
        this.width = height;
        addMouseListener(this);
        field = new Field(n, width);
    }

    public void repaint(Rectangle area) {
        int x = (int) area.getX();
        int y = (int) area.getY();
        int width = (int) area.getWidth();
        int height = (int) area.getHeight();
        repaint(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        setSize(field.getBounds().getSize());
        System.out.println(getWidth());
        System.out.println(field.getBounds());
        field.redraw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Rectangle area = field.clicked(e);
        repaint(area);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }
}
