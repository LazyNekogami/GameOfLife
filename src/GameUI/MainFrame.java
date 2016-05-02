package GameUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    //bounds
    private final int width = 720;
    private final int height = 540;
    int x;
    int y;

    //components
    private Canvas canv;
    private JButton startButton = new JButton("Start!");


    MainFrame() {
        calculateScreenCenter();
        setBounds(x, y, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        canv = new Canvas();
        canv.setSize(540, 540);
        add(canv);
        canv.setBackground(Color.GREEN);

        startButton = new JButton("Start!!");
        add(startButton);
    }

    public static void main(String agrs[]) {
        new MainFrame();
    }

    private void calculateScreenCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) (screenSize.getWidth() - width) / 2;
        y = (int) (screenSize.getHeight() - height) / 2;
    }
}
