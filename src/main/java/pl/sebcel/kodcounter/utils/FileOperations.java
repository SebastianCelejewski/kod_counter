package pl.sebcel.kodcounter.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pl.sebcel.kodcounter.domain.Project;

public class FileOperations {

    public void saveProject(File file, Project project) {
        try {
            JAXBContext context = JAXBContext.newInstance(Project.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(project, file);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save file: " + ex.getMessage(), ex);
        }
    }

    public Project loadProject(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Project.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Project project = (Project) unmarshaller.unmarshal(file);
            return project;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load file: " + ex.getMessage(), ex);
        }
    }
}