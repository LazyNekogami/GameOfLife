package GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener, AdjustmentListener {
    // Bounds
    protected final int DEFAULT_WIDTH = 720;
    protected final int DEFAULT_HEIGHT = 540;
    protected int x;
    protected int y;

    // Components
    protected Canvas canv;

    protected JPanel infoAndControlPanel = new JPanel(new BorderLayout());
    protected JPanel controlPanel = new JPanel();
    protected JPanel infoPanel = new JPanel();

    protected JButton startButton = new JButton("Start!");
    protected JButton stopButton = new JButton("Stop!");
    protected JButton stepButton = new JButton("Step");

    protected JLabel numberScrollLabel = new JLabel("Number of cells in row:");
    protected JScrollBar numberScroll = new JScrollBar(Adjustable.HORIZONTAL, 13, 1, 2, 20);
    protected JLabel delayScrollLabel = new JLabel("Delay:");
    protected JScrollBar delayScroll = new JScrollBar(Adjustable.HORIZONTAL, 500, 50, 150, 2000);


    protected JLabel aliveLabel = new JLabel("Currently alive: NaN");
    protected JLabel generationLabel = new JLabel("Current generation: NaN");

    MainFrame() {
        // Main Frame
        calculateScreenCenter();
        setBounds(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setVisible(true);

        // Canvas
        canv = new Canvas();
        canv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateInfo();
            }
        });
        add(canv);
        canv.setBackground(new Color(92, 00, 92));
        setVisible(true);
        //TODO: somehow fix bug causing not appearing infoAndControlPanel until window iconified and deiconified :/

        // Panels
        add(infoAndControlPanel);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoAndControlPanel.add(controlPanel, BorderLayout.CENTER);
        infoAndControlPanel.add(infoPanel, BorderLayout.PAGE_END);
        setVisible(true);

        // Button
        startButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        stopButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        stepButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(stepButton);
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        stepButton.addActionListener(this);
        stopButton.setVisible(false);
        setVisible(true);

        // ScrollBar
        controlPanel.add(numberScrollLabel);
        controlPanel.add(numberScroll);
        numberScroll.addAdjustmentListener(this);
        controlPanel.add(delayScrollLabel);
        controlPanel.add(delayScroll);
        delayScroll.addAdjustmentListener(this);

        // Labels
        infoPanel.add(aliveLabel);
        infoPanel.add(generationLabel);
        setVisible(true);
    }

    public static void main(String agrs[]) {
        new MainFrame();
    }

    private void calculateScreenCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) (screenSize.getWidth() - DEFAULT_WIDTH) / 2;
        y = (int) (screenSize.getHeight() - DEFAULT_HEIGHT) / 2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Random rnd = new Random(); // let it be
        if (source.equals(startButton)) {
            System.out.println("Start button pressed");
            startButton.setVisible(false);
            stopButton.setVisible(true);
            stepButton.setEnabled(false);
            numberScroll.setEnabled(false);
            delayScroll.setEnabled(false);
        }
        if (source.equals(stepButton)) {
            System.out.println("Step button pressed");
            canv.step();
            updateInfo();
//            setVisible(true);
        }
        if (source.equals(stopButton)) {
            System.out.println("Stop button pressed");
            //TODO: Make start/stop buttons functional
            startButton.setVisible(true);
            stopButton.setVisible(false);
            stepButton.setEnabled(true);
            numberScroll.setEnabled(true);
            delayScroll.setEnabled(true);
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        Object source = e.getSource();
        if (source.equals(numberScroll)) {
            canv.setN(numberScroll.getValue());
            updateInfo();
            canv.repaint();
        }
    }

    private void updateInfo() {
        aliveLabel.setText("Currently alive: " + canv.alive());
        generationLabel.setText("Current generation: " + canv.generation());
    }
}
