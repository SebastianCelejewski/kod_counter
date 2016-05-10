package pl.sebcel.kodcounter;

import java.awt.Point;
import java.util.List;

public class FrameData {
    private int frameIdx;
    private int peopleFlow;
    private List<Point> pepoleLocation;
    
    public int getPeopleFlow() {
        return peopleFlow;
    }
    
    public void setPeopleFlow(int peopleFlow) {
        this.peopleFlow = peopleFlow;
    }

}