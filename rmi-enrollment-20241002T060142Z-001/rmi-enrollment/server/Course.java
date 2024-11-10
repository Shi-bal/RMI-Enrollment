
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Course extends UnicastRemoteObject implements CourseInterface {
    private String id;
    private String name;

    public Course(String id, String name) throws RemoteException {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
