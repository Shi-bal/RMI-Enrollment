import java.rmi.RemoteException;

public class Faculty implements FacultyInterface {
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
