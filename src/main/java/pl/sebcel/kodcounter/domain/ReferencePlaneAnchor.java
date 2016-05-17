package pl.sebcel.kodcounter.domain;

public class ReferencePlaneAnchor {

    private int x;
    private int y;

    public ReferencePlaneAnchor() {
    }

    public ReferencePlaneAnchor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}