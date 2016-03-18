import java.awt.*;
import java.awt.event.MouseEvent;

public class Grid {
    final int psz = 1; //pen size = 1px
    final int csz; //cell size
    final int n; // number of cells in each grid row & column
    Rectangle bounds;
    Cell[][] cls;

    Grid(int n, int x0, int y0, int width, int height) {
        /**
         * Creates maximal cell-grid starting in point (x0, y0) bounded by width & height
         * Caution! Grid might be SMALLER than given bounding box.
         */
        this.n = n;
        csz = (width - psz) / n; // width = n * (psz + csz) + psz;
        int dx = csz + psz;
        int dy = dx;

        bounds = new Rectangle(x0, y0, dx * n + psz, dy * n + psz);

        int npoints = 4;
        cls = new Cell[n][n];
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
        System.out.println("bounds of gird = " + bounds);
    }

    private void drawSquareGrid(Graphics g) {
        /**
         * Utility method that draws empty rectangular grid
         */
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

    public void clicked(MouseEvent e) {
        int ex = e.getX() - bounds.x;
        if (ex < 0 || ex >= bounds.width) return;

        int ey = e.getY() - bounds.y;
        if (ey < 0 || ey >= bounds.height) return;
        int dx = csz + psz;
        int dy = dx;

        int j = ex / dx;
        int i = ey / dy;
        cls[i][j].reverseState();
    }

    public Rectangle clicked(MouseEvent e, Graphics g) {
        int ex = e.getX() - bounds.x;
        int ey = e.getY() - bounds.y;
        int dx = csz + psz;
        int dy = dx;

        int j = ex / dx;
        int i = ey / dy;
        cls[i][j].reverseState();
        return cls[i][j].draw(g);
    }

    void draw(Graphics g) {
        /**
         * Draws field: grid & cells in it
         */

        for (int i = 0; i < cls.length; i++) {
            for (int j = 0; j < cls[i].length; j++) {
                cls[i][j].draw(g);
            }
        }
        drawSquareGrid(g);
    }
}
