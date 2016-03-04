import java.awt.*;
import java.util.HashSet;

public class Cell {
    CellState state;
    CellState new_state;
    Polygon shape;
    HashSet<Cell> adj;

    Cell(Polygon shape) {
        this.shape = shape;
        state = CellState.DEAD;
    }

    void addNeighbor(Cell neighbor) {
        adj.add(neighbor);
    }

    void changeState() {
        state = new_state;
    }

    void draw(Graphics g) {
        switch (state) {
            case ALIVE:
                g.setColor(Color.YELLOW);
                break;
            case DEAD:
                g.setColor(Color.RED);
                break;
        }
        g.fillPolygon(shape);
    }

    @Override
    public String toString() {
        return "Cell[state=" + state + ",new_state=" + new_state + ",shape=" + shape + ",adj=" + adj + "]";
    }
}
