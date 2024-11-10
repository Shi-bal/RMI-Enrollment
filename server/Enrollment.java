import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;

public class Enrollment extends UnicastRemoteObject implements EnrollmentInterface {
    private List<Student> students = new ArrayList<>();
    private List<Faculty> faculty = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public Enrollment() throws RemoteException {
        super();
    }

    @Override
    public void enrollStudent(String studentId, String courseId) throws RemoteException {
        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        if (student != null && course != null) {
            student.setEnrolledCourse(course.getName());
        }
    }

    @Override
    public void addFaculty(String name) throws RemoteException {
        faculty.add(new Faculty(name));
    }

    @Override
    public void assignFacultyToStudent(String studentId, String facultyName) throws RemoteException {
        Student student = findStudent(studentId);
        if (student != null) {
            student.setAssignedFaculty(facultyName);
        }
    }

    @Override
    public List<Student> getUnenrolledStudents() throws RemoteException {
        List<Student> unenrolled = new ArrayList<>();
        for (Student student : students) {
            if (student.getEnrolledCourse() == null) {
                unenrolled.add(student);
            }
        }
        return unenrolled;
    }

    @Override
    public List<Student> getEnrolledStudentsWithoutFaculty() throws RemoteException {
        List<Student> withoutFaculty = new ArrayList<>();
        for (Student student : students) {
            if (student.getEnrolledCourse() != null && student.getAssignedFaculty() == null) {
                withoutFaculty.add(student);
            }
        }
        return withoutFaculty;
    }

    @Override
    public List<Student> getEnrolledStudentsWithFaculty() throws RemoteException {
        List<Student> withFaculty = new ArrayList<>();
        for (Student student : students) {
            if (student.getEnrolledCourse() != null && student.getAssignedFaculty() != null) {
                withFaculty.add(student);
            }
        }
        return withFaculty;
    }

    private Student findStudent(String id) {
        for (Student student : students) {
            try {
                if (student.getId().equals(id)) {
                    return student;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Course findCourse(String id) {
        for (Course course : courses) {
            try {
                if (course.getId().equals(id)) {
                    return course;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Methods to add students and courses (called by XMLWatcher)
    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}