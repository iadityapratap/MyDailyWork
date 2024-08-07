import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Class to represent a course
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean isAvailable() {
        return enrolledStudents < capacity;
    }

    public void enrollStudent() {
        if (isAvailable()) {
            enrolledStudents++;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    @Override
    public String toString() {
        return courseCode + ": " + title + "\nDescription: " + description + "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + (capacity - enrolledStudents);
    }
}

// Class to represent a student
class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.isAvailable()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for course: " + course.title);
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped course: " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
}

// Main class to run the program
public class CourseRegistrationSystem {
    private static HashMap<String, Course> courses = new HashMap<>();
    private static HashMap<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding some courses
        courses.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "MWF 10-11 AM"));
        courses.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to calculus", 25, "TTh 9-10:30 AM"));

        // Adding some students
        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));

        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. List Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    registerForCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    dropCourse(studentID, courseCode);
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(String studentID, String courseCode) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private static void dropCourse(String studentID, String courseCode) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }
}
