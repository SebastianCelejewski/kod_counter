package pl.sebcel.kodcounter.adhoc;

import org.junit.Test;

import pl.sebcel.kodcounter.FileOperations;
import pl.sebcel.kodcounter.FrameData;
import pl.sebcel.kodcounter.Project;

public class SerializeProjectToXML {

    @Test
    public void serializeProjectToXML() {
        Project project = new Project();
        project.setStartFrame(5);
        project.setEndFrame(7);
        project.setImagesDirectory("c:\\images\\test");
        project.addFrameData(5,  new FrameData());
        FileOperations fileOperations = new FileOperations();
        String serializedProject = fileOperations.serializeProject(project);
        System.out.println(serializedProject);
    }
}