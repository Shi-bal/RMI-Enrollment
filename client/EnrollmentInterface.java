import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EnrollmentInterface extends Remote {
    void enrollStudent(String studentId, String courseId) throws RemoteException;
    void addFaculty(String name) throws RemoteException;
    void assignFacultyToStudent(String studentId, String facultyName) throws RemoteException;
    List<StudentInterface> getUnenrolledStudents() throws RemoteException;
    List<StudentInterface> getEnrolledStudentsWithoutFaculty() throws RemoteException;
    List<StudentInterface> getEnrolledStudentsWithFaculty() throws RemoteException;
}
