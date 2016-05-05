package GameUI;

import Mechanics.Grid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends java.awt.Canvas implements MouseListener {
    // Bounds
    protected int x = 0;
    protected int y = 0;
    protected int width = 540;
    protected int height = 540;

    protected Grid grid;

    Canvas() {
        super();
        setBounds(x, y, width, height);
        setSize(width, height);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
