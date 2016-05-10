package pl.sebcel.kodcounter;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private int idx = 0;
    private File[] files = null;
    private JLabel label = new JLabel();
    private JPanel infoPanel = new JPanel();
    private NavigationPanel navigationPanel = new NavigationPanel();
    private DataPanel dataPanel = new DataPanel();

    private Data data = new Data();

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        mainFrame.setBounds(100, 100, 1920 / 2, 1080 / 2);
        mainFrame.init("c:\\Users\\Sebastian\\Desktop\\kod-2");

        mainFrame.setVisible(true);
    }

    public MainFrame() {
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(navigationPanel);
        infoPanel.add(dataPanel);
        navigationPanel.setMainFrame(this);

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);
    }

    public void init(String directoryPath) {
        File directory = new File(directoryPath);
        files = directory.listFiles();
        idx = 100;

        navigationPanel.setNumberOfFrames(files.length);
        data = new Data();
        dataPanel.setData(data);
        navigationPanel.setData(data);

        repaint();
    }

    public void repaint() {
        File file = files[idx];
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        label.setIcon(icon);
    }

    public void setFrameIdx(int frameIdx) {
        this.idx = frameIdx;
        dataPanel.setFrameIdx(frameIdx);
        repaint();
    }
}