package pl.sebcel.kodcounter.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.sebcel.kodcounter.domain.Project;
import pl.sebcel.kodcounter.domain.ReferencePlaneAnchor;
import pl.sebcel.kodcounter.events.NavigationListener;

public class FrameDisplay extends JPanel implements NavigationListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;

    private JLabel label = new JLabel();
    private ReferencePlane referencePlane = new ReferencePlane();

    private File[] movieClipFrames;
    private int currentFrameIdx;
    private Project project;

    public FrameDisplay() {
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
        this.addMouseMotionListener(this);
    }

    public void setProject(Project project) {
        this.project = project;

        if (project.getReferencePlaneStartPoint() != null) {
            referencePlane.setStartPoint(project.getReferencePlaneStartPoint());
        } else {
            referencePlane.setStartPoint(new ReferencePlaneAnchor(10, 10));
        }
        if (project.getReferencePlaneEndPoint() != null) {
            referencePlane.setEndPoint(project.getReferencePlaneEndPoint());
        } else {
            referencePlane.setEndPoint(new ReferencePlaneAnchor(10, 90));
        }
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

    public void paint(Graphics g) {
        super.paint(g);
        referencePlane.paint(g);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        referencePlane.setMouseLocation(e.getPoint().x, e.getPoint().y);
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        referencePlane.setAnchorLocation(e.getPoint().x, e.getPoint().y);
        project.setReferencePlaneStartPoint(referencePlane.getStartPoint());
        project.setReferencePlaneEndPoint(referencePlane.getEndPoint());
        this.repaint();
    }
}