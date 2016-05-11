package pl.sebcel.kodcounter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class Controller {

    private Project project = new Project();
    private FileOperations fileOperations = new FileOperations();
    
    private NavigationPanel navigationPanel;
    private DataPanel dataPanel;
    private FrameDisplay frameDisplay;
    private MainMenu mainMenu;
    private MainFrame mainFrame;
    
    public static void main(String[] args) {
        new Controller();
    }
    
    public Controller() {
        navigationPanel = new NavigationPanel();
        dataPanel = new DataPanel();
        frameDisplay = new FrameDisplay();
        mainMenu = new MainMenu();
        navigationPanel.setController(this);
        mainMenu.setController(this);

        mainFrame = new MainFrame(navigationPanel, dataPanel, frameDisplay, mainMenu);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(1000, 750);
        mainFrame.setLocation((screenSize.width - 1000) / 2, (screenSize.height - 750) / 2);
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