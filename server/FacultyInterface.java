import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FacultyInterface extends Remote {
    String getName() throws RemoteException;
}
