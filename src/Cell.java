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

    void draw(Graphics g) {
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
    }

    @Override
    public String toString() {
        return "Cell[state=" + state + ",new_state=" + new_state + ",shape=" + shape + "]";
    }
}
