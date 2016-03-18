import java.awt.*;

public class Cell {
    CellState state;
    CellState new_state;
    Polygon shape;
    Grid grid;

    Cell(Polygon shape) {
        this.shape = shape;
        state = CellState.DEAD;
    }

    Cell(Polygon shape, Grid grid) {
        this(shape);
        this.grid = grid;
    }

    void changeState() {
        state = new_state;
    }

    void reverseState() {
        switch (state) {
            case ALIVE:
                new_state = CellState.DEAD;
                break;
            case DEAD:
                new_state = CellState.ALIVE;
                break;
            default:
                System.err.println("Not specified state of the cell");
        }
        changeState();
    }

    void reverseState(Graphics g) {
        System.out.println("state = " + state + "shape = " + shape);
        reverseState();
        draw(g);
    }

    Rectangle draw(Graphics g) {
        Color tmp = g.getColor();
        switch (state) {
            case ALIVE:
                g.setColor(Color.YELLOW);
                break;
            case DEAD:
                g.setColor(Color.RED);
                break;
        }
        g.fillPolygon(shape);
        g.setColor(tmp);
        return shape.getBounds();
    }

    @Override
    public String toString() {
        return "Cell[state=" + state + ",new_state=" + new_state + ",shape=" + shape + "]";
    }
}
