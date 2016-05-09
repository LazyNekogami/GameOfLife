package Mechanics;

import java.awt.*;

public enum CellState {
    ALIVE(new Color(222, 177, 214)),
    DEAD(new Color(219, 39, 64));

    private Color color;

    CellState(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
