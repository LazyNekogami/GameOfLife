package Mechanics;

import GameUI.GraphicalCell;

import java.util.HashSet;

public class NewCell {
    private CellState state;
    private CellState new_state;
    private Field field;
    private HashSet<NewCell> adj; //adjecent cellst to current
    private GraphicalCell shape;

    public NewCell() {
        adj = new HashSet<>(8);
        state = CellState.DEAD;
    }

    public void computeNewState() {
        int counter = 0;
        for (NewCell cell : adj) {
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

    public void reverseState() {
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

    public GraphicalCell getShape() {
        return shape;
    }

    public void setShape(GraphicalCell shape) {
        this.shape = shape;
    }

    public CellState getState() {
        return state;
    }

    public boolean changeState() {
        CellState old_state = state;
        state = new_state;
        return !state.equals(old_state);
    }

    public void addAdj(NewCell cell) {
        adj.add(cell);
    }

    public HashSet<NewCell> getAdj() {
        return adj;
    }

}
