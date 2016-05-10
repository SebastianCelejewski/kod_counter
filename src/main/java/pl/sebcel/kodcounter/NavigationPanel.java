package pl.sebcel.kodcounter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NavigationPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private MainFrame mainFrame;

    private JButton firstFrame = new JButton("First");
    private JButton startFrame = new JButton("Start");
    private JButton left100 = new JButton("<<<");
    private JButton left10 = new JButton("<<");
    private JButton left1 = new JButton("<");
    private JTextField currentFrame = new JTextField();
    private JLabel totalFramewLabel = new JLabel();
    private JButton right1 = new JButton(">");
    private JButton right10 = new JButton(">>");
    private JButton right100 = new JButton(">>>");
    private JButton endFrame = new JButton("End");
    private JButton lastFrame = new JButton("Last");

    private int numberOfFrames = 0;
    private Project project;
    private int currentFrameIdx = 0;

    public NavigationPanel() {
        add(firstFrame);
        add(startFrame);
        add(left100);
        add(left10);
        add(left1);
        add(new JLabel("Frame "));
        add(currentFrame);
        add(new JLabel(" of "));
        add(totalFramewLabel);
        add(right1);
        add(right10);
        add(right100);
        add(endFrame);
        add(lastFrame);

        currentFrame.setPreferredSize(new Dimension(50, 21));
        totalFramewLabel.setPreferredSize(new Dimension(50, 21));

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

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setNumberOfFrames(int numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
        this.totalFramewLabel.setText(Integer.toString(numberOfFrames));
        this.currentFrame.setText(Integer.toString(currentFrameIdx));
    }

    private void setFrameIdx(int frameIdx) {
        currentFrameIdx = frameIdx;
        if (currentFrameIdx < 0) {
            currentFrameIdx = 0;
        }
        if (currentFrameIdx >= numberOfFrames) {
            currentFrameIdx = numberOfFrames - 1;
        }
        mainFrame.setFrameIdx(currentFrameIdx);
        this.currentFrame.setText(Integer.toString(currentFrameIdx));
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
        mainFrame.setFrameIdx(currentFrameIdx);
        this.currentFrame.setText(Integer.toString(currentFrameIdx));
        this.repaint();
    }
}