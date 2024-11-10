import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

public class XMLWatcher implements Runnable {
    private Path xmlPath;
    private Registry registry;
    private String type;

    public XMLWatcher(Path xmlPath, Registry registry, String type) {
        this.xmlPath = xmlPath;
        this.registry = registry;
        this.type = type;
    }

    @Override
    public void run() {
        // Implement the run method using the class fields
    }

    private void parseXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlPath.toFile());

            NodeList nodeList = doc.getElementsByTagName(type.toLowerCase());
            Enrollment enrollment = (Enrollment) registry.lookup("Enrollment");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (type.equals("Students")) {
                    String id = element.getAttribute("id");
                    String name = element.getAttribute("name");
                    String program = element.getAttribute("program");
                    enrollment.addStudent(new Student(id, name, program));
                } else if (type.equals("Courses")) {
                    String id = element.getAttribute("id");
                    String name = element.getAttribute("name");
                    enrollment.addCourse(new Course(id, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}