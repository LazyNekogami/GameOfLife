package GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
    // Bounds
    protected final int width = 720;
    protected final int height = 540;
    protected int x;
    protected int y;

    // Components
    protected Canvas canv;

    protected JButton startButton = new JButton("Start!");
    protected JButton stepButton = new JButton("Step");

    protected JPanel infoAndControlPanel = new JPanel(new BorderLayout());
    protected JPanel controlPanel = new JPanel();
    protected JPanel infoPanel = new JPanel();

    protected JLabel aliveLabel = new JLabel("Currently alive: NaN");
    protected JLabel generationLabel = new JLabel("Current generation: NaN");

    MainFrame() {
        // Main Frame
        calculateScreenCenter();
        setBounds(x, y, width, height);
//        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // Canvas
        int canvSize = getContentPane().getHeight();
        canv = new Canvas(canvSize);
        add(canv);
        canv.setBackground(Color.GREEN);
        canv.setSize(canvSize, canvSize);
//        Dimension maxSize = new Dimension(canv.width, canv.height);
//        Dimension minSize = new Dimension(canv.width-200, canv.height-200);
//        canv.setMaximumSize(maxSize);
//        canv.setMinimumSize(minSize);

        // Panels
//        add(infoAndControlPanel);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoAndControlPanel.add(controlPanel, BorderLayout.CENTER);
        infoAndControlPanel.add(infoPanel, BorderLayout.PAGE_END);

        // Button
        startButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        stepButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        controlPanel.add(startButton);
        controlPanel.add(stepButton);
        startButton.addActionListener(this);
        stepButton.addActionListener(this);

        // Labels
        infoPanel.add(aliveLabel);
        infoPanel.add(generationLabel);

        // And finishing touch
        setVisible(true);
    }

    public static void main(String agrs[]) {
        new MainFrame();
    }

    private void calculateScreenCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) (screenSize.getWidth() - width) / 2;
        y = (int) (screenSize.getHeight() - height) / 2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Random rnd = new Random();
        if (source.equals(startButton)) {
            startButton.setBackground(Color.getHSBColor(rnd.nextFloat(), 1f, 1.0f));
            System.out.println(canv.getHeight());
        }
        if (source.equals(stepButton)) {
            stepButton.setBackground(Color.getHSBColor(rnd.nextFloat(), 1f, 1.0f));
        }
    }
}
