package pl.sebcel.kodcounter;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuFileNew = new JMenuItem("New");
    private JMenuItem menuFileOpen = new JMenuItem("Open");
    private JMenuItem menuFileSave = new JMenuItem("Save");
    private JMenuItem menuFileSaveAs = new JMenuItem("Save As");
    private JMenuItem menuFileClose = new JMenuItem("Close");

    private MainFrame mainFrame;
    private String projectFilename;

    public MainMenu() {
        menuFile.add(menuFileNew);
        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);
        menuFile.add(menuFileSaveAs);
        menuFile.add(menuFileClose);

        this.add(menuFile);

        menuFileNew.addActionListener(e -> newProject());
        menuFileSaveAs.addActionListener(e -> saveProjectAs());
        menuFileSave.addActionListener(e -> saveProject());
        menuFileOpen.addActionListener(e -> openProject());
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void newProject() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select directory containing set of images");
        if (fileChooser.showDialog(MainMenu.this, "Select images directory") == JFileChooser.APPROVE_OPTION) {
            String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
            mainFrame.initProject(selectedDirectory);
            projectFilename = null;
        }
    }

    private void saveProjectAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(MainMenu.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            projectFilename = file.getAbsolutePath();
            mainFrame.saveProject(file);
        }
    }

    private void saveProject() {
        if (projectFilename == null) {
            saveProjectAs();
        } else {
            mainFrame.saveProject(new File(projectFilename));
        }
    }

    private void openProject() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(MainMenu.this) == JFileChooser.APPROVE_OPTION) {
            mainFrame.openProject(fileChooser.getSelectedFile());
            projectFilename = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }
}