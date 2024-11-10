import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CourseInterface extends Remote {
    String getId() throws RemoteException;
    String getName() throws RemoteException;
}
