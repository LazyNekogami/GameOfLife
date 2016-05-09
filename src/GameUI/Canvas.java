package GameUI;

import Mechanics.Field;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends java.awt.Canvas implements MouseListener {
    // Graphics
    protected int fieldOffset;
    protected int fieldWidth;
    protected int n = 13;
    protected Polygon[][] polys;
    // Mechanics
    protected Field field;

    Canvas() {
        super();
        addMouseListener(this);
        field = new Field(n);
        polys = new Polygon[n][n];
    }

    Canvas(int n) {
        super();
        this.n = n;
        addMouseListener(this);
        field = new Field(n);
        polys = new Polygon[n][n];
    }

    public void repaint(Rectangle area) {
        int x = (int) area.getX();
        int y = (int) area.getY();
        int width = (int) area.getWidth();
        int height = (int) area.getHeight();
        repaint(x, y, width, height);
    }

    private void computeFieldWidth() {
        int psz = 1;
        int dx = (getWidth() - psz) / n; // DEFAULT_WIDTH = n * (psz + csz) + psz;
        fieldWidth = dx * n + psz;
        fieldOffset = (getWidth() - fieldWidth) / 2;
    }

    @Override
    public void paint(Graphics g) {
        setSize(getHeight(), getHeight());
        System.out.println("cavasHeight=" + getHeight());
        computeFieldWidth();
        int psz = 1;
        int dx = (getWidth() - psz) / n; // DEFAULT_WIDTH = n * (psz + csz) + psz;
        int npoints = 4;
        for (int i = 0, y = fieldOffset; i < n; i++, y += dx) {
            for (int j = 0, x = fieldOffset; j < n; j++, x += dx) {
                int[] xpoints = {
                        (x + psz),
                        (x + dx),
                        (x + dx),
                        (x + psz)
                };
                int[] ypoints = {
                        (y + psz),
                        (y + psz),
                        (y + dx),
                        (y + dx)
                };
                polys[i][j] = new Polygon(xpoints, ypoints, npoints);
                g.setColor(Color.PINK);
                g.fillPolygon(polys[i][j]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

//        repaint(area);
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
