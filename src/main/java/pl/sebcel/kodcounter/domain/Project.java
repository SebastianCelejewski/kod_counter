package pl.sebcel.kodcounter.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {

    @XmlElement
    private String imagesDirectory;

    @XmlElement
    private Integer startFrameIdx;

    @XmlElement
    private Integer endFrameIdx;

    @XmlElement
    private ReferencePlaneAnchor referencePlaneStartPoint;

    @XmlElement
    private ReferencePlaneAnchor referencePlaneEndPoint;

    @XmlElement
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

    public ReferencePlaneAnchor getReferencePlaneStartPoint() {
        return referencePlaneStartPoint;
    }

    public void setReferencePlaneStartPoint(ReferencePlaneAnchor referencePlaneStartPoint) {
        this.referencePlaneStartPoint = referencePlaneStartPoint;
    }

    public ReferencePlaneAnchor getReferencePlaneEndPoint() {
        return referencePlaneEndPoint;
    }

    public void setReferencePlaneEndPoint(ReferencePlaneAnchor referencePlaneEndPoint) {
        this.referencePlaneEndPoint = referencePlaneEndPoint;
    }
}