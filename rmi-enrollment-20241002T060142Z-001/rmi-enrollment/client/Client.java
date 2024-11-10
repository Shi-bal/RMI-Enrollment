import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
            EnrollmentInterface enrollment = (EnrollmentInterface) registry.lookup("Enrollment");

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n1. Enroll student to course");
                System.out.println("2. Add faculty");
                System.out.println("3. Assign faculty to student");
                System.out.println("4. Display unenrolled students");
                System.out.println("5. Display enrolled students without faculty");
                System.out.println("6. Display enrolled students with faculty");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter course ID: ");
                        String courseId = scanner.nextLine();
                        enrollment.enrollStudent(studentId, courseId);
                        System.out.println("Student enrolled successfully.");
                        break;
                    case 2:
                        System.out.print("Enter faculty name: ");
                        String facultyName = scanner.nextLine();
                        enrollment.addFaculty(facultyName);
                        System.out.println("Faculty added successfully.");
                        break;
                    case 3:
                        System.out.print("Enter student ID: ");
                        studentId = scanner.nextLine();
                        System.out.print("Enter faculty name: ");
                        facultyName = scanner.nextLine();
                        enrollment.assignFacultyToStudent(studentId, facultyName);
                        System.out.println("Faculty assigned to student successfully.");
                        break;
                    case 4:
                        List<StudentInterface> unenrolled = enrollment.getUnenrolledStudents();
                        System.out.println("Unenrolled students:");
                        for (StudentInterface student : unenrolled) {
                            System.out.println(student.getName() + " (" + student.getId() + ")");
                        }
                        break;
                    case 5:
                        List<StudentInterface> enrolledWithoutFaculty = enrollment.getEnrolledStudentsWithoutFaculty();
                        System.out.println("Enrolled students without faculty:");
                        for (StudentInterface student : enrolledWithoutFaculty) {
                            System.out.println(student.getName() + " (" + student.getId() + ")");
                        }
                        break;
                    case 6:
                        List<StudentInterface> enrolledWithFaculty = enrollment.getEnrolledStudentsWithFaculty();
                        System.out.println("Enrolled students with faculty:");
                        for (StudentInterface student : enrolledWithFaculty) {
                            System.out.println(student.getName() + " (" + student.getId() + ") - " + student.getFaculty());
                        }
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);

            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}