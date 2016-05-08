package Deprecated;

import Mechanics.CellState;

import java.awt.*;
import java.util.HashSet;

public class Cell {
    final Color _DESERT_BROWN = new Color(237, 201, 175);
    final Color _GREEN = new Color(34, 139, 34);
    CellState state;
    CellState new_state;
    Polygon shape;
    Grid grid;
    HashSet<Cell> adj; //adjacent cells to current

    public Cell(Polygon shape) {
        adj = new HashSet<>(8);
        this.shape = shape;
        state = CellState.DEAD;
    }

    public Cell(Polygon shape, Grid grid) {
        this(shape);
        this.grid = grid;
    }

    public CellState getState() {
        return state;
    }

    public HashSet<Cell> getAdj() {
        return adj;
    }

    public void addAdj(Cell cell) {
        adj.add(cell);
    }

    public void changeState() {
        state = new_state;
    }

    public void computeNewState() {
        int counter = 0;
        for (Cell cell : adj) {
            if (cell.getState().equals(CellState.ALIVE)) counter++;
        }

        //Killing alive or Reviving dead one
        if (this.state.equals(CellState.ALIVE)) {
            if (counter < 2 || counter > 3) new_state = CellState.DEAD;
            else new_state = state;
        } else if (this.state.equals(CellState.DEAD)) {
            if (counter == 3) new_state = CellState.ALIVE;
            else new_state = state;
        }
    }

    public Rectangle reverseState() {
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
        return shape.getBounds();
    }

    public Rectangle reverseState(Graphics g) {
        reverseState();
        return draw(g);
    }

    /**
     * Debug method
     */
    public void reverseStateOfAllAdj(Graphics g) {
        for (Cell c : adj) {
            c.reverseState(g);
        }
    }

    public Rectangle draw(Graphics g) {
        Color tmp = g.getColor();
        switch (state) {
            case ALIVE:
                g.setColor(_GREEN);
                break;
            case DEAD:
                g.setColor(_DESERT_BROWN);
                break;
        }
        g.fillPolygon(shape);
        g.setColor(tmp);
        return shape.getBounds();
    }

    @Override
    public String toString() {
        return "Deprecated.Cell[state=" + state + ",new_state=" + new_state + ",shape=" + shape + "]";
    }
}
