import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseCode;
    private String title;
    private int capacity;
    private List<String> students;

    public Course(String courseCode, String title, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getStudents() {
        return students;
    }

    public boolean registerStudent(String studentName) {
        if (students.size() < capacity) {
            students.add(studentName);
            System.out.println(studentName + " has been registered for " + title);
            return true;
        } else {
            System.out.println("Sorry, " + title + " is already full.");
            return false;
        }
    }

    public void dropStudent(String studentName) {
        if (students.remove(studentName)) {
            System.out.println(studentName + " has been dropped from " + title);
        } else {
            System.out.println(studentName + " is not registered in " + title);
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\n" +
                "Title: " + title + "\n" +
                "Capacity: " + capacity + "\n" +
                "Registered Students: " + students.size() + "/" + capacity;
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("--------------------");
        }
    }

    public void registerStudentForCourse(String courseCode, String studentName) {
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            course.registerStudent(studentName);
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void dropStudentFromCourse(String courseCode, String studentName) {
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            course.dropStudent(studentName);
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Adding sample courses
        Course course1 = new Course("CHEM101", "Introduction to Chemistry", 30);
        Course course2 = new Course("PHYS101", "Physics Fundamentals", 25);

        registrationSystem.addCourse(course1);
        registrationSystem.addCourse(course2);

        // Displaying available courses
        registrationSystem.displayCourseListing();

        // Sample registrations and drops
        registrationSystem.registerStudentForCourse("CHEM101", "Alice");
        registrationSystem.registerStudentForCourse("PHYS101", "Bob");
        registrationSystem.registerStudentForCourse("CHEM101", "Charlie");

        registrationSystem.dropStudentFromCourse("PHYS101", "Bob");

        // Displaying final state of courses
        System.out.println("\nFinal Course Listing:");
        registrationSystem.displayCourseListing();
    }
}
