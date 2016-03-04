import java.awt.*;

public class Drawer {
    /**
     * Deprecated
     */
    Graphics g;

    public Drawer(Graphics g) {
        this.g = g;
    }

    void drawSquareGrid(int x0, int y0, int width, int height, int n) {
        int psz = 1; //pen size = 1px

        // Vertical lines
        double dx = (width - x0 + psz); // dx = csz + psz
        dx /= n;
        System.err.println("dx " + dx);
        for (double x = x0; x < width; x += dx) {
            int i = (int) x;
            g.drawLine(i, y0, i, height - psz);
        }
        g.drawLine(width - psz, y0, width - psz, height - psz);

        // Horizontal lines
        double dy = (height - y0 + psz); // dy = csz + psz
        dy /= n;
        System.err.println("dy " + dy);
        for (double y = y0; y < height; y += dy) {
            int j = (int) y;
            g.drawLine(x0, j, width - psz, j);
        }
        g.drawLine(x0, height - psz, width - psz, height - psz);
    }

}