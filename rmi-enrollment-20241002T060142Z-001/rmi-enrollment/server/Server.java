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

public class Server {
    public static void main(String[] args) {
                try {
                    System.setProperty("java.rmi.server.hostname", "127.0.0.1");
                    Registry registry = LocateRegistry.createRegistry(9100);

                    String studentsXmlPath = "C:/Users/Admin/Desktop/rmi-enrollment/server/students.xml";
                    String coursesXmlPath = "C:/Users/Admin/Desktop/rmi-enrollment/server/courses.xml";

                    XMLWatcher studentsWatcher = new XMLWatcher(Paths.get(studentsXmlPath), registry, "Students");
                    XMLWatcher coursesWatcher = new XMLWatcher(Paths.get(coursesXmlPath), registry, "Courses");

                    Thread studentsThread = new Thread(studentsWatcher);
                    Thread coursesThread = new Thread(coursesWatcher);

                    studentsThread.start();
                    coursesThread.start();

                    Enrollment enrollment = new Enrollment();
                    EnrollmentInterface stubEnrollment;
                    try {
                        stubEnrollment = (EnrollmentInterface) java.rmi.server.UnicastRemoteObject.exportObject(enrollment, 0);
                    } catch (java.rmi.server.ExportException e) {
                        // Object is already exported, so we can use it as is
                        stubEnrollment = (EnrollmentInterface) enrollment;
                    }
                    registry.rebind("Enrollment", stubEnrollment);

                    System.out.println("Server is running...");
                } catch (Exception e) {
                    System.err.println("Server exception: " + e.toString());
                    e.printStackTrace();
                }
    }
}
