//TASK 5//
import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    public void enroll() {
        if (isAvailable()) {
            enrolled++;
        }
    }

    public void drop() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nEnrolled: " + enrolled + "\nAvailable Slots: " + (capacity - enrolled);
    }
}

class Student {
    String studentId;
    String name;
    List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.isAvailable()) {
            course.enroll();
            registeredCourses.add(course);
            System.out.println("Successfully registered for: " + course.title);
        } else {
            System.out.println("Sorry, no available slots for: " + course.title);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.drop();
            registeredCourses.remove(course);
            System.out.println("Successfully dropped: " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            System.out.println(name + "'s Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();

    public static void addSampleCourses() {
        courses.add(new Course("CS101", "Intro to Computer Science", "Learn the basics of computer science.", 30));
        courses.add(new Course("MATH101", "Calculus I", "Learn the fundamentals of calculus.", 25));
        courses.add(new Course("PHY101", "Physics I", "Introduction to basic physics concepts.", 20));
    }

    public static void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("\n" + course);
        }
    }

    public static void main(String[] args) {
        addSampleCourses();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Course Registration System!");
        System.out.println("Enter your name: ");
        String studentName = scanner.nextLine();
        System.out.println("Enter your student ID: ");
        String studentId = scanner.nextLine();

        Student student = new Student(studentId, studentName);
        students.add(student);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu: ");
            System.out.println("1. View available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. View my registered courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    System.out.println("Enter the course code you want to register for:");
                    String courseCode = scanner.nextLine();
                    Course courseToRegister = null;
                    for (Course course : courses) {
                        if (course.courseCode.equalsIgnoreCase(courseCode)) {
                            courseToRegister = course;
                            break;
                        }
                    }
                    if (courseToRegister != null) {
                        student.registerCourse(courseToRegister);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the course code you want to drop:");
                    courseCode = scanner.nextLine();
                    Course courseToDrop = null;
                    for (Course course : courses) {
                        if (course.courseCode.equalsIgnoreCase(courseCode)) {
                            courseToDrop = course;
                            break;
                        }
                    }
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    student.displayCourses();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the Course Registration System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
