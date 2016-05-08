package Deprecated;

import Mechanics.CellState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class Grid {
    final int psz = 1; //pen size = 1px
    final int csz; //cell size
    final int n; // number of cells in each field row & column
    final Rectangle bounds;
    Cell[][] cls;

    /**
     * Creates maximal cell-field starting in point (x0, y0) bounded by width & height
     * Caution! Deprecated.Field might be SMALLER than given bounding box.
     */
    public Grid(int n, int x0, int y0, int width, int height) {
        this.n = n;
        csz = (width - psz) / n - psz; // width = n * (psz + csz) + psz;
        int dx = csz + psz;
        int dy = dx;

        System.err.println(width + " " + dx + " " + n + " " + dx * n);
        bounds = new Rectangle(x0, y0, dx * n + psz, dy * n + psz);

        int npoints = 4;
        cls = new Cell[n][n];

        //Initializing each cell in table cls[0..n][0..n]
        for (int i = 0, y = y0; i < n; i++, y += dy) {
            for (int j = 0, x = x0; j < n; j++, x += dx) {
                int[] xpoints = {
                        (x + psz),
                        (x + dx),
                        (x + dx),
                        (x + psz)
                };
                int[] ypoints = {
                        (y + psz),
                        (y + psz),
                        (y + dy),
                        (y + dy)
                };
                cls[i][j] = new Cell(new Polygon(xpoints, ypoints, npoints), this);
            }
        }

        //Binding all 9-s of cells together
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bindAdjs(i, j);
            }
        }
    }

    public Grid(int n, int width) {
        this(n, 0, 0, width, width);
    }

    /**
     * Binds all adjacent cells in a Moore neighborhood to cls[i][j]
     */
    private void bindAdjs(int i, int j) {
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                int adj_i = (i + k + n) % n;
                int adj_j = (j + l + n) % n;
                if (adj_i == i && adj_j == j) continue;
                Cell adj = cls[adj_i][adj_j];
                cls[i][j].addAdj(adj);
            }
        }
    }

    /**
     * Utility method that draws empty rectangular field
     */
    private void drawSquareGrid(Graphics g) {
        int x0 = bounds.x;
        int y0 = bounds.y;
        int x1 = x0 + bounds.width - 1;
        int y1 = y0 + bounds.height - 1;
        int dx = psz + csz;
        int dy = dx;

        g.setColor(Color.BLACK);

        // Vertical lines
        for (int j = 0, x = x0; j < n + 1; j++, x += dx) {
            g.drawLine(x, y0, x, y1);
        }
        // Horizontal lines
        for (int i = 0, y = y0; i < n + 1; i++, y += dy) {
            g.drawLine(x0, y, x1, y);
        }
    }

    public Rectangle clicked(MouseEvent e) {
        int ex = e.getX() - bounds.x;
        if (ex < 0 || ex >= bounds.width) return new Rectangle(0, 0);

        int ey = e.getY() - bounds.y;
        if (ey < 0 || ey >= bounds.height) return new Rectangle(0, 0);
        int dx = csz + psz;
        int dy = dx;

        int j = ex / dx;
        int i = ey / dy;
        return cls[i][j].reverseState();
    }

    public Rectangle clicked(MouseEvent e, Graphics g) {
        int ex = e.getX() - bounds.x;
        if (ex < 0 || ex >= bounds.width) return new Rectangle(0, 0);

        int ey = e.getY() - bounds.y;
        if (ey < 0 || ey >= bounds.height) return new Rectangle(0, 0);
        int dx = csz + psz;
        int dy = dx;

        int j = ex / dx;
        int i = ey / dy;
        return cls[i][j].reverseState(g);
    }

    /**
     * Another debug method
     */
    public void clickedDebug(MouseEvent e, Graphics g) {

        int ex = e.getX() - bounds.x;
        if (ex < 0 || ex >= bounds.width) return;

        int ey = e.getY() - bounds.y;
        if (ey < 0 || ey >= bounds.height) return;
        int dx = csz + psz;
        int dy = dx;

        int j = ex / dx;
        int i = ey / dy;
        cls[i][j].reverseStateOfAllAdj(g);
    }

    public void step(Graphics g) {
        HashSet<Cell> list = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Cell cell = cls[i][j];
                if (cell.getState().equals(CellState.ALIVE)) {
                    list.addAll(cell.getAdj());
                    list.add(cell);
                }
            }
        }

        System.out.println(list);
        for (Cell cell : list) cell.computeNewState();
        for (Cell cell : list) {
            cell.changeState();
            cell.draw(g);
        }
//        return list;
    }

    /**
     * Draws field: field & cells in it
     */
    public void redraw(Graphics g) {
        for (int i = 0; i < cls.length; i++) {
            for (int j = 0; j < cls[i].length; j++) {
                cls[i][j].draw(g);
            }
        }
//        drawSquareGrid(g);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
