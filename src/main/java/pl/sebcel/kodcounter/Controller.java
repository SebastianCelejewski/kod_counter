package pl.sebcel.kodcounter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class Controller {

    private Project project;
    private FileOperations fileOperations;

    private NavigationPanel navigationPanel;
    private DataPanel dataPanel;
    private FrameDisplay frameDisplay;
    private MainMenu mainMenu;
    private MainFrame mainFrame;

    public static void main(String[] args) {
        new Controller();
    }

    public Controller() {
        fileOperations = new FileOperations();

        navigationPanel = new NavigationPanel();
        dataPanel = new DataPanel();
        frameDisplay = new FrameDisplay();
        mainMenu = new MainMenu();
        navigationPanel.setController(this);
        mainMenu.setController(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(1000, 750);
        mainFrame = new MainFrame(navigationPanel, dataPanel, frameDisplay, mainMenu);
        mainFrame.setSize(frameSize);
        mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        mainFrame.setVisible(true);
    }

    public void initProject(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        frameDisplay.setFiles(files);
        frameDisplay.setFrameIdx(0);
        navigationPanel.setNumberOfFrames(files.length);
        project = new Project();
        project.setImagesDirectory(directoryPath);
        dataPanel.setProject(project);
        navigationPanel.setProject(project);

        mainFrame.repaint();
    }

    public void saveProject(File file) {
        fileOperations.saveProject(file, project);
    }

    public void openProject(File file) {
        project = fileOperations.loadProject(file);

        File directory = new File(project.getImagesDirectory());
        File[] files = directory.listFiles();

        frameDisplay.setFiles(files);
        frameDisplay.setFrameIdx(0);
        navigationPanel.setNumberOfFrames(files.length);

        dataPanel.setProject(project);
        navigationPanel.setProject(project);

        mainFrame.repaint();
    }

    public void setFrameIdx(int frameIdx) {
        frameDisplay.setFrameIdx(frameIdx);
        dataPanel.setFrameIdx(frameIdx);
    }
}