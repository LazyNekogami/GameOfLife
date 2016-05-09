package Mechanics;

import java.awt.*;
import java.util.HashSet;

public class Field implements Runnable {

    private final long MAX_DELAY = 2500;
    private final long MIN_DELAY = 150;
    private int n = 13;
    private NewCell[][] cells;
    // Runnable fields
    private HashSet<NewCell> checkList;
    private long delay = 500; // delay = 500 ms
    private volatile boolean isRunning = false;

    public Field() {
        cells = new NewCell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new NewCell();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bindAdjs(i, j);
            }
        }
    }

    public Field(int n) {
        this.n = n;
        cells = new NewCell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new NewCell();
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
                NewCell adj = cells[adj_i][adj_j];
                cells[i][j].addAdj(adj);
            }
        }
    }

    private void computeCheckList() {
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

    public HashSet<Polygon> step() {
        computeCheckList();
        HashSet<Polygon> res = new HashSet<>(checkList.size());
        for (NewCell cell : checkList) {
            cell.computeNewState();
            res.add(cell.getShape());
        }
        for (NewCell cell : checkList) {
            cell.changeState();
        }
        return res;
    }

    private void unpreparedStep() {
        if (checkList == null) {
            computeCheckList();
        }
        HashSet<NewCell> newList = new HashSet<>();
        for (NewCell cell : checkList) {
            cell.computeNewState();
        }
        for (NewCell cell : checkList) {
            cell.changeState();
            if (cell.getState().equals(CellState.ALIVE)) {
                newList.addAll(cell.getAdj());
                newList.add(cell);
            }
        }
        checkList = newList;
    }

    @Override
    public void run() {
        try {
            computeCheckList();
            while (isRunning && !checkList.isEmpty()) {
                unpreparedStep();
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void setShape(int i, int j, Polygon shape) {
        cells[i][j].setShape(shape);
    }
}
