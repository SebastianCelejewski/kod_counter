package pl.sebcel.kodcounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Project {

    private String imagesDirectory;
    private Integer startFrameIdx;
    private Integer endFrameIdx;
    private Map<Integer, FrameData> frameData = new HashMap<Integer, FrameData>();

    public String getImagesDirectory() {
        return imagesDirectory;
    }

    public void setImagesDirectory(String imagesDirectory) {
        this.imagesDirectory = imagesDirectory;
    }

    public FrameData getFrameData(int frameIdx) {
        return frameData.get(frameIdx);
    }

    public void setStartFrame(int startFrameIdx) {
        this.startFrameIdx = startFrameIdx;
    }

    public void setEndFrame(int endFrameIdx) {
        this.endFrameIdx = endFrameIdx;
    }

    public Integer getStartFrameIdx() {
        return startFrameIdx;
    }

    public Integer getEndFrameIdx() {
        return endFrameIdx;
    }

    public void addFrameData(int currentFrameIdx, FrameData frame) {
        frameData.put(currentFrameIdx, frame);
    }

    public Set<Integer> getFrameDataIdxs() {
        return frameData.keySet();
    }
}