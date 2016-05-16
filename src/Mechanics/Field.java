package Mechanics;

import java.util.HashSet;

public class Field {

    private final long MAX_DELAY = 2500;
    private final long MIN_DELAY = 150;
    public int alive = 0;
    public int generation = 0;
    // Runnable fields
    public HashSet<Cell> checkList;
    private int n = 13;
    private Cell[][] cells;
    private long delay = 500; // delay = 500 ms

    public Field(int n) {
        this.n = n;
        cells = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bindAdjs(i, j);
            }
        }
    }

    private void bindAdjs(int i, int j) {
        for (int v = -1; v < 2; v++) {
            for (int h = -1; h < 2; h++) {
                int adj_i = (i + v + n) % n;
                int adj_j = (j + h + n) % n;
                if (adj_i == i && adj_j == j) continue;
                Cell adj = cells[adj_i][adj_j];
                cells[i][j].addAdj(adj);
            }
        }
    }

    public void prepareCheckList() {
        checkList = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CellState current = cells[i][j].getState();
                if (CellState.ALIVE.equals(current)) {
                    checkList.add(cells[i][j]);
                    checkList.addAll(cells[i][j].getAdj());
                }
            }
        }
    }

    public HashSet<Cell> step() {
        prepareCheckList();
        HashSet<Cell> changedCells = new HashSet<>();
        for (Cell cell : checkList) {
            cell.computeNewState();
        }
        for (Cell cell : checkList) {
            if (cell.changeState()) {
                changedCells.add(cell);
                if (cell.getState().equals(CellState.ALIVE)) {
                    alive++;
                } else alive--;
            }
        }
        if (changedCells.size() > 0) {
            generation++;
        }
        return changedCells;
    }

    public void unpreparedStep() {
        if (checkList == null) {
            prepareCheckList();
        }
        HashSet<Cell> newList = new HashSet<>();
        for (Cell cell : checkList) {
            cell.computeNewState();
        }
        for (Cell cell : checkList) {
            if (cell.changeState()) {
                newList.addAll(cell.getAdj());
                newList.add(cell);
                if (cell.getState().equals(CellState.ALIVE)) {
                    alive++;
                } else alive--;
            }
        }
        if (newList.size() > 0) {
            generation++;
        }
        checkList = newList;
    }

    public void reverseState(int i, int j) {
        cells[i][j].reverseState();
        CellState state = cells[i][j].getState();
        if (state.equals(CellState.ALIVE)) {
            alive++;
        } else alive--;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        if (delay < MIN_DELAY) {
            this.delay = MIN_DELAY;
            return;
        }
        if (delay > MAX_DELAY) {
            this.delay = MAX_DELAY;
            return;
        }
        this.delay = delay;
    }
}
