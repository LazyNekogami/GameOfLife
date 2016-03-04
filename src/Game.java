
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class Game extends JFrame {
    private static final long serialVersionUID = -5621534979740641657L;

    Rectangle bounds = new Rectangle(100, 100, 600, 600);
    MyCanvas canv;
    MovingPoint mp = new MovingPoint(new Point(300, 300));
    JLabel label;

    public Game() {
        init();
    }

    private void init() {
        setAndShowGUI();
        MyMouseAdapter listener = new MyMouseAdapter(canv);
        MyKeyAdaptor keyListener = new MyKeyAdaptor(mp);
        canv.addMouseListener(listener);
        canv.addMouseMotionListener(listener);
        canv.addKeyListener(keyListener);
//        canv.repaint();
    }

    public void setAndShowGUI() {
        this.setVisible(true);
        setBounds(bounds);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canv = new MyCanvas();
        canv.setSize(bounds.width - 100, bounds.height - 100);
        canv.setBackground(Color.LIGHT_GRAY);
        add(canv);

//        label = new JLabel("Default label text");
//        label.setLabelFor(canv);

        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

    }

    private class MyKeyAdaptor extends KeyAdapter {
        MovingPoint mp;

        MyKeyAdaptor() {
        }

        ;

        MyKeyAdaptor(MovingPoint mp) {
            this.mp = mp;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 37:
                    mp.move(-1, 0);
                    break;
                case 38:
                    mp.move(0, -1);
                    break;
                case 39:
                    mp.move(1, 0);
                    System.out.println("Right");
                    break;
                case 40:
                    System.out.println("Down");
                    mp.move(0, 1);
                    break;
            }
            System.out.println(e.getKeyCode());
            canv.repaint();
//            canv.repaint(50,50,300,300);
        }
    }

    public class MyMouseAdapter extends MouseInputAdapter {
        MyCanvas canv;

        public MyMouseAdapter(MyCanvas canv) {
            this.canv = canv;
        }

        public void mouseDragged(MouseEvent e) {
//            canv.drawRndColoredLine(e);
        }

    }


    private class MyCanvas extends Canvas {
        Graphics g;
        Random rnd = new Random();
        Grid grid;

        MyCanvas() {
            super();
            grid = new Grid(13, new Rectangle(10, 10, 290, 290));
        }

        void drawRndColoredLine(MouseEvent e) {
            /**
             * Funny useless method
             */
            g = getGraphics();
            g.setColor(new Color(rnd.nextInt(0xffffff + 1)));
            g.drawLine(rnd.nextInt(getWidth()), rnd.nextInt(getHeight()), e.getX(), e.getY());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            grid.draw(g);
            mp.draw(g);
//            drawGrid(g);
        }

//        void drawGrid(Graphics g) {
//            new Drawer(g).drawSquareGrid(0, 0, 500, 500, 50);
//        }
    }

    public static void main(String[] args) {
        new Game();
    }

}