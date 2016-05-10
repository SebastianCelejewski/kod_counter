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
import javax.swing.border.TitledBorder;

public class DataPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Project project;
    private JTextField peopleFlowField = new JTextField();
    private JButton setStartFrame = new JButton("Set as start frame");
    private JButton setEndFrame = new JButton("Set as end frame");
    private JLabel dataLabel = new JLabel();

    private int currentFrameIdx;

    public DataPanel() {
        setBorder(new TitledBorder("Data panel"));

        add(new JLabel("Denoted people: "));
        add(peopleFlowField);
        add(setStartFrame);
        add(setEndFrame);
        add(dataLabel);

        peopleFlowField.setPreferredSize(new Dimension(50, 21));
        dataLabel.setPreferredSize(new Dimension(500, 21));

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
        refreshView();
    }

    public void setFrameIdx(int frameIdx) {
        currentFrameIdx = frameIdx;
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

        if (project.getStartFrameIdx() != null && project.getEndFrameIdx() != null) {
            int startFrameIdx = project.getStartFrameIdx();
            int endFrameIdx = project.getEndFrameIdx();

            int totalPeopleFlow = 0;
            int dataFrames = 0;

            for (Integer frameIdx : project.getFrameDataIdxs()) {
                if (frameIdx >= startFrameIdx && frameIdx < endFrameIdx) {
                    frameData = project.getFrameData(frameIdx);
                    dataFrames += 1;
                    totalPeopleFlow += frameData.getPeopleFlow();
                }
            }

            int estimatedTotalPeople = (int) (totalPeopleFlow * (new Double(endFrameIdx - startFrameIdx) / new Double(dataFrames)));

            String dataText = "Analysed frames: " + dataFrames;
            dataText += ", people denoted: " + totalPeopleFlow;
            dataText += ", estimated number of people: " + estimatedTotalPeople;

            dataLabel.setText(dataText);
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
    }
}