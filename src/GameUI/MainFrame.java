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
    private JButton stepButton = new JButton("Step");

    private JPanel infoAndControlPanel = new JPanel(new BorderLayout());
    private JPanel controlPanel = new JPanel();
    private JPanel infoPanel = new JPanel(new FlowLayout());

    private JLabel aliveLabel = new JLabel("Currently alive: ");

    MainFrame() {
        calculateScreenCenter();
        setBounds(x, y, width, height);
//        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        System.out.println(getContentPane().getHeight());
        System.out.println(getContentPane().getWidth());

        setLayout(new BorderLayout());
//        setLayout(null);

        canv = new Canvas();
//        canv.setMinimumSize(new Dimension(540,540));
        add(canv, BorderLayout.LINE_START);
//        add(canv);
        canv.setBackground(Color.GREEN);

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));

        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));

//        controlPanel.setPreferredSize(new Dimension(150, 540));
//        startButton = new JButton("Start!!");
//        startButton.setSize(100,30);
//        controlPanel.add(startButton);
        add(infoAndControlPanel, BorderLayout.CENTER);
        infoAndControlPanel.add(controlPanel, BorderLayout.CENTER);
        infoAndControlPanel.add(infoPanel, BorderLayout.PAGE_END);

        controlPanel.add(startButton);
        controlPanel.add(stepButton);

        infoPanel.add(aliveLabel);
//        pack();
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
