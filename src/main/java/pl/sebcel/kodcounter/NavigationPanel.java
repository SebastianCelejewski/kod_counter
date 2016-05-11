package pl.sebcel.kodcounter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class NavigationPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Controller controller;

    private JButton firstFrame = new JButton("First");
    private JButton startFrame = new JButton("Start");
    private JButton left100 = new JButton("<<<");
    private JButton left10 = new JButton("<<");
    private JButton left1 = new JButton("<");
    private JLabel frameInfoLabel = new JLabel();
    private JButton right1 = new JButton(">");
    private JButton right10 = new JButton(">>");
    private JButton right100 = new JButton(">>>");
    private JButton endFrame = new JButton("End");
    private JButton lastFrame = new JButton("Last");

    private int numberOfFrames = 0;
    private Project project;
    private int currentFrameIdx = 0;

    public NavigationPanel() {
        setBorder(new TitledBorder("Navigation"));

        add(firstFrame);
        add(startFrame);
        add(left100);
        add(left10);
        add(left1);
        add(frameInfoLabel);
        add(right1);
        add(right10);
        add(right100);
        add(endFrame);
        add(lastFrame);

        frameInfoLabel.setPreferredSize(new Dimension(120, 21));

        firstFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFrameIdx(0);
            }
        });
        startFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (project.getStartFrameIdx() != null) {
                    setFrameIdx(project.getStartFrameIdx());
                }
            }
        });
        left100.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFrameIdx(-100);
            }
        });
        left10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFrameIdx(-10);
            }
        });
        left1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFrameIdx(-1);
            }
        });
        right1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFrameIdx(1);
            }
        });
        right10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFrameIdx(10);
            }
        });
        right100.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFrameIdx(100);
            }
        });
        endFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (project.getEndFrameIdx() != null) {
                    setFrameIdx(project.getEndFrameIdx());
                }
            }
        });
        lastFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFrameIdx(numberOfFrames - 1);
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setNumberOfFrames(int numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
        this.frameInfoLabel.setText("Frame " + currentFrameIdx + " of " + numberOfFrames);
    }

    private void setFrameIdx(int frameIdx) {
        currentFrameIdx = frameIdx;
        if (currentFrameIdx < 0) {
            currentFrameIdx = 0;
        }
        if (currentFrameIdx >= numberOfFrames) {
            currentFrameIdx = numberOfFrames - 1;
        }
        controller.setFrameIdx(currentFrameIdx);
        this.frameInfoLabel.setText("Frame " + currentFrameIdx + " of " + numberOfFrames);
        this.repaint();
    }

    private void updateFrameIdx(int delta) {
        currentFrameIdx += delta;
        if (currentFrameIdx < 0) {
            currentFrameIdx = 0;
        }
        if (currentFrameIdx >= numberOfFrames) {
            currentFrameIdx = numberOfFrames - 1;
        }
        controller.setFrameIdx(currentFrameIdx);
        this.frameInfoLabel.setText("Frame " + currentFrameIdx + " of " + numberOfFrames);
        this.repaint();
    }
}