package pl.sebcel.kodcounter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Project project;
    private JLabel frameIdxLabel = new JLabel();
    private JTextField peopleFlowField = new JTextField();
    private JButton setStartFrame = new JButton("Set as start frame");
    private JButton setEndFrame = new JButton("Set as end frame");
    private JLabel startFrameLabel = new JLabel();
    private JLabel endFrameLabel = new JLabel();

    private int currentFrameIdx;

    public DataPanel() {
        add(new JLabel("Frame idx: "));
        add(frameIdxLabel);
        add(new JLabel("People flow: "));
        add(peopleFlowField);
        add(setStartFrame);
        add(setEndFrame);
        add(new JLabel("Start frame idx: "));
        add(startFrameLabel);
        add(new JLabel("End frame idx: "));
        add(endFrameLabel);

        peopleFlowField.setPreferredSize(new Dimension(50, 21));
        startFrameLabel.setPreferredSize(new Dimension(50, 21));
        endFrameLabel.setPreferredSize(new Dimension(50, 21));

        setStartFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                project.setStartFrame(currentFrameIdx);
                refreshView();
            }
        });

        setEndFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                project.setEndFrame(currentFrameIdx);
                refreshView();
            }
        });

        peopleFlowField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int peopleFlow = Integer.parseInt(peopleFlowField.getText());
                        peopleFlowField.setBackground(Color.WHITE);
                        setPeopleFlow(peopleFlow);
                    } catch (Exception ex) {
                        peopleFlowField.setBackground(Color.RED);
                    }
                }
            }
        });
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setFrameIdx(int frameIdx) {
        currentFrameIdx = frameIdx;
        frameIdxLabel.setText(Integer.toString(frameIdx));
        refreshView();
    }

    private void refreshView() {
        FrameData frameData = project.getFrameData(currentFrameIdx);
        peopleFlowField.setBackground(Color.WHITE);
        if (frameData != null) {
            peopleFlowField.setText(Integer.toString(frameData.getPeopleFlow()));
        } else {
            peopleFlowField.setText("");
        }
        if (project.getStartFrameIdx() != null) {
            startFrameLabel.setText(Integer.toString(project.getStartFrameIdx()));
        }
        if (project.getEndFrameIdx() != null) {
            endFrameLabel.setText(Integer.toString(project.getEndFrameIdx()));
        }
    }

    private void setPeopleFlow(int peopleFlow) {
        FrameData frameData = project.getFrameData(currentFrameIdx);
        if (frameData == null) {
            frameData = new FrameData();
            project.addFrameData(currentFrameIdx, frameData);
        }

        frameData.setPeopleFlow(peopleFlow);
        refreshView();

        calculateResults();
    }

    private void calculateResults() {
        if (project.getStartFrameIdx() == null) {
            System.out.println("You have to set start frame first");
            return;
        }

        if (project.getEndFrameIdx() == null) {
            System.out.println("You have to set end frame first");
        }

        int startFrameIdx = project.getStartFrameIdx();
        int endFrameIdx = project.getEndFrameIdx();

        int totalPeopleFlow = 0;
        int dataFrames = 0;

        for (Integer frameIdx : project.getFrameDataIdxs()) {
            if (frameIdx >= startFrameIdx && frameIdx < endFrameIdx) {
                dataFrames += 1;
                FrameData frameData = project.getFrameData(frameIdx);
                totalPeopleFlow += frameData.getPeopleFlow();
            }
        }

        System.out.println("Frames range length: " + (endFrameIdx - startFrameIdx));
        System.out.println("Data frames count: " + dataFrames);
        System.out.println("Total people flow: " + totalPeopleFlow);
    }
}