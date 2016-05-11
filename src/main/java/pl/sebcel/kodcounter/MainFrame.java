package pl.sebcel.kodcounter;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainFrame(NavigationPanel navigationPanel, DataPanel dataPanel, FrameDisplay frameDisplay, MainMenu mainMenu) {
        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(navigationPanel);
        infoPanel.add(dataPanel);

        this.setLayout(new BorderLayout());
        this.setJMenuBar(mainMenu);
        
        this.add(frameDisplay, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.NORTH);

        this.setTitle("KOD Counter");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}