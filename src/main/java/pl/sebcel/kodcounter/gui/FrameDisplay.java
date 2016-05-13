package pl.sebcel.kodcounter.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameDisplay extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel label = new JLabel();

    private File[] movieClipFrames;
    private int currentFrameIdx;

    public FrameDisplay() {
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
    }

    public void setFiles(File[] movieClipFrames) {
        this.movieClipFrames = movieClipFrames;
    }

    public void setFrameIdx(int frameIdx) {
        this.currentFrameIdx = frameIdx;
        this.repaint();
    }

    public void repaint() {
        if (movieClipFrames != null) {
            File file = movieClipFrames[currentFrameIdx];
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            label.setIcon(icon);
        }
    }
}