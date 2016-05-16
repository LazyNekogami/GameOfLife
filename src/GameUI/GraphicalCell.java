package GameUI;

import Mechanics.Cell;

import java.awt.*;

public class GraphicalCell extends Polygon {
    private Cell cell;

    public GraphicalCell(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }

    public static void link(GraphicalCell shape, Cell cell) {
        cell.setShape(shape);
        shape.setCell(cell);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void draw(Graphics g) {
        Color tmpclr = g.getColor();
        Color color = cell.getState().getColor();
        g.setColor(color);
        g.fillPolygon(this);
        g.setColor(tmpclr);
    }
}
