package pl.sebcel.kodcounter;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private MainMenu mainMenu = new MainMenu();
    private FileOperations fileOperations = new FileOperations();

    private Project project = new Project();

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setBounds(100, 100, 1920 / 2, 1080 / 2);
        mainFrame.setVisible(true);
    }

    public MainFrame() {
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(navigationPanel);
        infoPanel.add(dataPanel);

        navigationPanel.setMainFrame(this);
        mainMenu.setMainFrame(this);

        this.setLayout(new BorderLayout());
        this.setJMenuBar(mainMenu);
        this.add(label, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.NORTH);

        this.setTitle("KOD Counter");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void initProject(String directoryPath) {
        File directory = new File(directoryPath);
        files = directory.listFiles();
        idx = 100;

        navigationPanel.setNumberOfFrames(files.length);
        project = new Project();
        project.setImagesDirectory(directoryPath);
        dataPanel.setProject(project);
        navigationPanel.setProject(project);

        repaint();
    }

    public void saveProject(File file) {
        fileOperations.saveProject(file, project);
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

    public Project getProject() {
        return project;
    }
}