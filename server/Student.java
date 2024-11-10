
import java.rmi.RemoteException;

public class Student implements StudentInterface {
    private String id;
    private String name;
    private String program;
    private String enrolledCourse;
    private String assignedFaculty;

    public Student(String id, String name, String program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getProgram() throws RemoteException {
        return program;
    }

    @Override
    public String getEnrolledCourse() throws RemoteException {
        return enrolledCourse;
    }

    @Override
    public void setEnrolledCourse(String course) throws RemoteException {
        this.enrolledCourse = course;
    }

    @Override
    public String getAssignedFaculty() throws RemoteException {
        return assignedFaculty;
    }

    @Override
    public void setAssignedFaculty(String faculty) throws RemoteException {
        this.assignedFaculty = faculty;
    }

    @Override
    public String getFaculty() throws RemoteException {
        return getAssignedFaculty();
    }
}