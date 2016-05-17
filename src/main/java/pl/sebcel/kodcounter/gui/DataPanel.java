package pl.sebcel.kodcounter.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import pl.sebcel.kodcounter.domain.FrameData;
import pl.sebcel.kodcounter.domain.Project;
import pl.sebcel.kodcounter.events.NavigationListener;

public class DataPanel extends JPanel implements NavigationListener {

    private static final long serialVersionUID = 1L;

    private JTextField numberOfDenotedPeopleTextField = new JTextField();
    private JButton setStartFrameButton = new JButton("Set as start frame");
    private JButton setEndFrameButton = new JButton("Set as end frame");
    private JLabel informationLabel = new JLabel();

    private Project project;
    private int currentFrameIdx;

    public DataPanel() {
        setBorder(new TitledBorder("Data panel"));

        add(new JLabel("Denoted people: "));
        add(numberOfDenotedPeopleTextField);
        add(setStartFrameButton);
        add(setEndFrameButton);
        add(informationLabel);

        numberOfDenotedPeopleTextField.setPreferredSize(new Dimension(50, 21));
        informationLabel.setPreferredSize(new Dimension(500, 21));

        setStartFrameButton.addActionListener(e -> setStartFrame(currentFrameIdx));
        setEndFrameButton.addActionListener(e -> setEndFrame(currentFrameIdx));

        numberOfDenotedPeopleTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int peopleFlow = Integer.parseInt(numberOfDenotedPeopleTextField.getText());
                        numberOfDenotedPeopleTextField.setBackground(Color.WHITE);
                        setNumberOfPeople(peopleFlow);
                        refreshView();
                    } catch (Exception ex) {
                        numberOfDenotedPeopleTextField.setBackground(Color.RED);
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

    private void setStartFrame(int startFrameIdx) {
        project.setStartFrame(currentFrameIdx);
        refreshView();
    }

    private void setEndFrame(int endFrameIdx) {
        project.setEndFrame(currentFrameIdx);
        refreshView();
    }

    private void refreshView() {
        FrameData frameData = project.getFrameData(currentFrameIdx);
        numberOfDenotedPeopleTextField.setBackground(Color.WHITE);
        if (frameData != null) {
            numberOfDenotedPeopleTextField.setText(Integer.toString(frameData.getNumberOfDenotedPeople()));
        } else {
            numberOfDenotedPeopleTextField.setText("");
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
                    totalPeopleFlow += frameData.getNumberOfDenotedPeople();
                }
            }

            int estimatedTotalPeople = (int) (totalPeopleFlow * (new Double(endFrameIdx - startFrameIdx) / new Double(dataFrames)));

            String dataText = "Analysed frames: " + dataFrames;
            dataText += ", people denoted: " + totalPeopleFlow;
            dataText += ", estimated number of people: " + estimatedTotalPeople;

            informationLabel.setText(dataText);
        }
    }

    private void setNumberOfPeople(int numberOfPeople) {
        FrameData frameData = project.getFrameData(currentFrameIdx);
        if (frameData == null) {
            frameData = new FrameData();
            project.addFrameData(currentFrameIdx, frameData);
        }

        frameData.setNumberOfDenotedPeople(numberOfPeople);
    }
}