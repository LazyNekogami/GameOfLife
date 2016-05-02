package GameUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends java.awt.Canvas implements MouseListener {
    //bounds
    private int x = 0;
    private int y = 0;
    private int width = 540;
    private int height = 540;

    Canvas() {
//        super();
        setBounds(x, y, width, height);
//        setBackground(Color.PINK);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
