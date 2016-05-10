package pl.sebcel.kodcounter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileOperations {

    public String serializeProject(Project project) {
        try {
            JAXBContext context = JAXBContext.newInstance(Project.class);
            Marshaller marshaller = context.createMarshaller();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(project, out);

            out.close();
            return new String(out.toByteArray());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to serialize project: " + ex.getMessage(), ex);
        }
    }

    public void saveProject(File file, Project project) {
        try {
            String serializedProject = serializeProject(project);
            FileWriter out = new FileWriter(file, false);
            out.write(serializedProject);
            out.close();
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