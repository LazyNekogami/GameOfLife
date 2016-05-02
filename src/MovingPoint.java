import java.awt.*;

public class MovingPoint {
    Point p;

    MovingPoint(Point p) {
        this.p = p;
    }

    public void draw(Graphics g) {
        Color temp = g.getColor();
        g.setPaintMode();
//        g.setXORMode(Color.GREEN);
        g.setColor(Color.GREEN);
        g.drawLine(0, 0, p.x, p.y);
        g.setColor(temp);
        temp = null;
    }

    void move(int x, int y) {
        p.translate(x, y);
        System.out.println(p);
    }
}
