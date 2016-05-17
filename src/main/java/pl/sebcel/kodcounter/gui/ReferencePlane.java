package pl.sebcel.kodcounter.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import pl.sebcel.kodcounter.domain.ReferencePlaneAnchor;

public class ReferencePlane extends JComponent {

    private static final long serialVersionUID = 1L;

    private ReferencePlaneAnchor startPoint;
    private ReferencePlaneAnchor endPoint;

    private boolean startPointSelected = false;
    private boolean endPointSelected = false;

    public void setStartPoint(ReferencePlaneAnchor p) {
        startPoint = p;
    }

    public void setEndPoint(ReferencePlaneAnchor p) {
        endPoint = p;
    }

    public void setMouseLocation(int x, int y) {
        if (startPoint != null) {
            startPointSelected = Point.distance(startPoint.getX(), startPoint.getY(), x, y) < 10;
        }
        if (endPoint != null) {
            endPointSelected = Point.distance(endPoint.getX(), endPoint.getY(), x, y) < 10;
        }
    }

    public void setAnchorLocation(int x, int y) {
        if (startPointSelected) {
            setStartPoint(new ReferencePlaneAnchor(x, y));
        }
        if (endPointSelected) {
            setEndPoint(new ReferencePlaneAnchor(x, y));
        }
    }

    public void paint(Graphics g) {
        if (startPoint != null && endPoint != null) {
            g.setColor(Color.yellow);
            g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());

            int radius = 10;

            if (startPointSelected) {
                g.drawOval(startPoint.getX() - radius / 2, startPoint.getY() - radius / 2, 10, 10);
            }
            if (endPointSelected) {
                g.drawOval(endPoint.getX() - radius / 2, endPoint.getY() - radius / 2, 10, 10);
            }
        }
    }

    public ReferencePlaneAnchor getStartPoint() {
        return startPoint;
    }

    public ReferencePlaneAnchor getEndPoint() {
        return endPoint;
    }
}