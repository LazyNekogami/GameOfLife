package GameUI;

import Mechanics.Grid;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends java.awt.Canvas implements MouseListener {
    // Bounds
    protected int width = 300;
    protected int n = 13;

    protected Grid grid;

    Canvas() {
        super();
//        setBounds(x, y, width, height);
//        setSize(width, height);
        addMouseListener(this);
//        grid = new Grid(n, x, y, width, height);
        grid = new Grid(n, width);
    }

    Canvas(int height) {
        super();
        this.width = height;
        addMouseListener(this);
        grid = new Grid(n, width);
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
        System.out.println(getWidth());
        System.out.println(grid.getBounds());
        grid.redraw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Rectangle area = grid.clicked(e);
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
