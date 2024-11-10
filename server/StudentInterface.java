import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInterface extends Remote {
    String getId() throws RemoteException;
    String getName() throws RemoteException;
    String getProgram() throws RemoteException;
    String getEnrolledCourse() throws RemoteException;
    void setEnrolledCourse(String course) throws RemoteException;
    String getAssignedFaculty() throws RemoteException;
    void setAssignedFaculty(String faculty) throws RemoteException;
    String getFaculty() throws RemoteException;
}
