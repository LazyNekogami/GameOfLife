import java.awt.*;

public class Grid {
    int psz = 1; //pen size = 1px
    //    int csz; //cell size
    Rectangle bounds;
    Cell[][] cls;


    Grid(int n, Rectangle bounds) {
        this.bounds = bounds;
        cls = new Cell[n][n];
        int npoints = 4;

        int x0 = bounds.x;
        double dx = ((double) bounds.width - psz) / n;
//        int[] xpoints = {psz, (int) (dx - 1), (int) (dx - 1), psz};
//        System.out.println("Grid dx " + dx);

        int y0 = bounds.y;
        double dy = ((double) bounds.height - psz) / n;
//        System.out.println("Grid dy " + dy);
//        int[] ypoints = {psz, psz, (int) (dy - 1), (int) (dy - 1)};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[] xpoints = {
                        (int) (x0 + psz + dx * j),
                        (int) (x0 + dx + dx * j),
                        (int) (x0 + dx + dx * j),
                        (int) (x0 + psz + dx * j)
                };
//                if (dx + dx * j >= bounds.width - 1) System.out.println("Alert " + j);
                int[] ypoints = {
                        (int) (y0 + psz + dy * i),
                        (int) (y0 + psz + dy * i),
                        (int) (y0 + dy + dy * i),
                        (int) (y0 + dy + dy * i)
                };
                Polygon shape = new Polygon(xpoints, ypoints, npoints);
                cls[i][j] = new Cell(shape);
            }
        }
    }

    private void drawSquareGrid(Graphics g) {
        /**
         * Utility method that draws empty rectangular grid
         */
        int x0 = bounds.x;
        int y0 = bounds.y;
        int x1 = bounds.width + x0;
        int y1 = bounds.height + y0;
        int n = cls.length;

        psz = 1; //pen size = 1px
        g.setColor(Color.BLACK);

        // Vertical lines
        double dx = (x1 - x0 - psz); // (width - psz) / n
        dx /= n;
        System.err.println("dx " + dx);
        for (double x = x0; x < x1; x += dx) {
            int i = (int) x;
            System.out.println("i x " + i + " " + x);
            g.drawLine(i, y0, i, y1 - 1);
        }
//        g.drawLine(x1 - psz, y0, x1 - psz, y1 - psz);

        // Horizontal lines
        double dy = (y1 - y0 - psz); // dy = csz + psz
        dy /= n;
        System.err.println("dy " + dy);
        for (double y = y0; y < y1; y += dy) {
            int j = (int) y;
            g.drawLine(x0, j, x1 - 1, j);
        }
//        g.drawLine(x0, y1 - psz, x1 - psz, y1 - psz);
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
