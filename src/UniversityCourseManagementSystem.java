import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public final class UniversityCourseManagementSystem {


    private UniversityCourseManagementSystem() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * Course of Bob.
     */
    private static final int BOB_COURSE = 3;
    /**
     * Course of Alex.
     */
    private static final int ALEX_COURSE = 4;
    /**
     * Course of Ahmed.
     */
    private static final int AHMED_COURSE = 4;
    /**
     * Course of Andrey.
     */
    private static final int ANDREY_COURSE = 5;
    /**
     * List of courses.
     */
    private static List<Course> courses;

    /**
     * List of students.
     */
    private static List<Student> students;

    /**
     * List of professors.
     */
    private static List<Professor> professors;
    /**
     * The main entry point for the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        fillInitialData();
        processCommands();
    }
    /**
     * Fills the initial data for courses, students, and professors.
     */
    public static void fillInitialData() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        professors = new ArrayList<>();

        courses.add(new Course("java_beginner", CourseLevel.BACHELOR));
        courses.add(new Course("java_intermediate", CourseLevel.BACHELOR));
        courses.add(new Course("python_basics", CourseLevel.BACHELOR));
        courses.add(new Course("algorithms", CourseLevel.MASTER));
        courses.add(new Course("advanced_programming", CourseLevel.MASTER));
        courses.add(new Course("mathematical_analysis", CourseLevel.MASTER));
        courses.add(new Course("computer_vision", CourseLevel.MASTER));

        students.add(new Student("Alice"));
        students.get(0).enroll(courses.get(0));
        students.get(0).enroll(courses.get(1));
        students.get(0).enroll(courses.get(2));

        students.add(new Student("Bob"));
        students.get(1).enroll(courses.get(0));
        students.get(1).enroll(courses.get(BOB_COURSE));

        students.add(new Student("Alex"));
        students.get(2).enroll(courses.get(ALEX_COURSE));

        professors.add(new Professor("Ali"));
        professors.get(0).teach(courses.get(0));
        professors.get(0).teach(courses.get(1));

        professors.add(new Professor("Ahmed"));
        professors.get(1).teach(courses.get(2));
        professors.get(1).teach(courses.get(AHMED_COURSE));

        professors.add(new Professor("Andrey"));
        professors.get(2).teach(courses.get(ANDREY_COURSE));
    }
    /**
     * Processes commands entered by the user.
     */
    public static void processCommands() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            try {
                if (command.startsWith(" ")) {
                    throw new IllegalArgumentException("Wrong inputs");
                }

                switch (command) {
                    case "course":
                        addCourse(scanner);
                        break;
                    case "student":
                        addStudent(scanner);
                        break;
                    case "professor":
                        addProfessor(scanner);
                        break;
                    case "enroll":
                        enrollStudent(scanner);
                        break;
                    case "drop":
                        dropStudent(scanner);
                        break;
                    case "teach":
                        assignProfessor(scanner);
                        break;
                    case "exempt":
                        exemptProfessor(scanner);
                        break;
                    case "":
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong inputs");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (Exception e) {
                System.out.println("Wrong inputs");
                System.exit(0);
            }
        }

        scanner.close();
    }
    /**
     * Adds a new course based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void addCourse(Scanner scanner) {
        try {
            String courseName = scanner.nextLine();

            if (courses.stream().anyMatch(c -> c.getCourseName().equalsIgnoreCase(courseName))) {
                throw new IllegalArgumentException("Course exists");
            }

            String courseLevelString = scanner.nextLine().trim().toLowerCase();

            CourseLevel courseLevel;

            try {
                courseLevel = CourseLevel.valueOf(courseLevelString.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Wrong inputs");
            }

            if (!isValidCourseName(courseName) || isInvalidCourseName(courseName)) {
                throw new IllegalArgumentException("Wrong inputs");
            }

            Course newCourse = new Course(courseName, courseLevel);

            courses.add(newCourse);
            System.out.println("Added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    /**
     * Checks if the course name is valid.
     * @param courseName is argument to check.
     * @return if all letters of word is english - true.
     */
    private static boolean isValidCourseName(String courseName) {
        return courseName.matches("[A-Za-z]+(_[A-Za-z]+)*") && !isCommandName(courseName);
    }
    /**
     * Checks if the course name is invalid.
     * @param name is argument to check.
     * @return if name is not command name.
     */
    private static boolean isCommandName(String name) {
        return name.equals("course") || name.equals("student") || name.equals("professor")
                || name.equals("enroll") || name.equals("drop") || name.equals("teach")
                || name.equals("exempt") || name.equals("");
    }
    /**
     * Adds a new student based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void addStudent(Scanner scanner) {
        try {
            String memberName = scanner.nextLine().trim();

            if (!isValidStudentName(memberName) || isInvalidStudentName(memberName)) {
                throw new IllegalArgumentException("Wrong inputs");
            }

            Student newStudent = new Student(memberName);
            students.add(newStudent);

            System.out.println("Added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    /**
     * Checks if the student name is valid.
     * @param studentName is argument to check.
     *@return if all letters of word is english - true.
     */
    private static boolean isValidStudentName(String studentName) {
        return studentName.matches("[A-Za-z]+") && !isCommandName(studentName);
    }
    /**
     * Adds a new professor based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void addProfessor(Scanner scanner) {
        try {
            String memberName = scanner.nextLine().trim();

            if (!isValidProfessorName(memberName) || isInvalidProfessorName(memberName)) {
                throw new IllegalArgumentException("Wrong inputs");
            }

            Professor newProfessor = new Professor(memberName);
            professors.add(newProfessor);

            System.out.println("Added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    /**
     * Checks if the course name is invalid.
     * @param courseName is argument to check.
     * @return check course name if it's not master or bachelor.
     */
    private static boolean isInvalidCourseName(String courseName) {
        return courseName.toLowerCase().contains("master") || courseName.toLowerCase().contains("bachelor");
    }
    /**
     * Checks if the student name is valid.
     * @param studentName is argument to check.
     * @return check student name if it's not master or bachelor.
     */
    private static boolean isInvalidStudentName(String studentName) {
        return studentName.equalsIgnoreCase("master") || studentName.equalsIgnoreCase("bachelor");
    }

    /**
     * Checks if the professor name is invalid.
     * @param professorName is argument to check.
     * @return check professor name if it's not master or bachelor.
     */
    private static boolean isInvalidProfessorName(String professorName) {
        return professorName.equalsIgnoreCase("master") || professorName.equalsIgnoreCase("bachelor");
    }
    /**
     * Checks if the professor name is valid.
     * @param professorName  is argument to check.
     * @return if all letters of professor name are english - true.
     */
    private static boolean isValidProfessorName(String professorName) {
        return professorName.matches("[A-Za-z]+") && !isCommandName(professorName);
    }

    /**
     * Enrolls a student in a course based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void enrollStudent(Scanner scanner) {
        try {
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            int courseId = Integer.parseInt(scanner.nextLine().trim());

            Student student = findStudentById(studentId);
            Course course = findCourseById(courseId);

            if (student != null && course != null) {
                if (student.getEnrolledCourses().contains(course)) {
                    throw new IllegalArgumentException("Student is already enrolled in this course");
                } else if (student.enroll(course)) {
                    System.out.println("Enrolled successfully");
                } else if (student.getEnrolledCourses().size() >= Student.getMaxEnrollment()) {
                    throw new IllegalArgumentException("Maximum enrollment is reached for the student");
                }
            } else {
                throw new IllegalArgumentException("Wrong inputs");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong inputs");
            System.exit(0);
        }
    }
    /**
     * Drops a student from a course based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void dropStudent(Scanner scanner) {
        try {
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            int courseId = Integer.parseInt(scanner.nextLine().trim());

            Student student = findStudentById(memberId);
            Course course = findCourseById(courseId);

            if (student != null && course != null) {
                if (student.drop(course)) {
                    System.out.println("Dropped successfully");
                } else {
                    throw new IllegalArgumentException("Student is not enrolled in this course");
                }
            } else {
                throw new IllegalArgumentException("Wrong inputs");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong inputs");
            System.exit(0);
        }
    }
    /**
     * Assigns a professor to teach a course based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void assignProfessor(Scanner scanner) {
        try {
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            int courseId = Integer.parseInt(scanner.nextLine().trim());

            Professor professor = findProfessorById(memberId);
            Course course = findCourseById(courseId);

            if (professor != null && course != null) {
                if (!professor.isTeaching(course) && professors.contains(professor) && courses.contains(course)) {
                    if (professor.teach(course)) {
                        System.out.println("Professor is successfully assigned to teach this course");
                    } else if (professor.getAssignedCourses().size() >= Professor.getMaxLoad()) {
                        throw new IllegalArgumentException("Professor's load is complete");
                    } else if (professor.isTeaching(course)) {
                        throw new IllegalArgumentException("Professor is already teaching this course");
                    }
                } else {
                    throw new IllegalArgumentException("Wrong inputs");
                }
            } else {
                throw new IllegalArgumentException("Wrong inputs");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong inputs");
            System.exit(0);
        }
    }

    /**
     * Exempts a professor from teaching a course based on user input.
     * @param scanner argument of the function to read information from console.
     */
    public static void exemptProfessor(Scanner scanner) {
        try {
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            int courseId = Integer.parseInt(scanner.nextLine().trim());

            Professor professor = findProfessorById(memberId);
            Course course = findCourseById(courseId);

            if (professor != null && course != null) {
                if (professor.exempt(course)) {
                    System.out.println("Professor is exempted");
                } else if (!professor.isTeaching(course)) {
                    throw new IllegalArgumentException("Professor is not teaching this course");
                }
            } else {
                throw new IllegalArgumentException("Wrong inputs");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong inputs");
            System.exit(0);
        }
    }

    /**
     * Finds a student by their member ID.
     * @param memberId is id of student.
     * @return student by his id.
     */
    public static Student findStudentById(int memberId) {
        for (Student student : students) {
            if (student.getMemberId() == memberId) {
                return student;
            }
        }
        return null;
    }
    /**
     * Finds a professor by their member ID.
     * @param memberId is id of professor.
     * @return return professor by his id.
     */
    public static Professor findProfessorById(int memberId) {
        for (Professor professor : professors) {
            if (professor.getMemberId() == memberId) {
                return professor;
            }
        }
        return null;
    }
    /**
     * Finds a course by their member ID.
     * @param courseId is id of course.
     * @return return course by his id.
     */
    public static Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }
}

enum CourseLevel {
    /**
     * Enumeration representing the level of a course (BACHELOR or MASTER).
     */
    BACHELOR, MASTER
}

interface Enrollable {
    boolean drop(Course course);

    boolean enroll(Course course);
}

abstract class UniversityMember {
    /**
     * Number of members in university.
     */
    private static int numberOfMembers = 0;
    /**
     * id of some member.
     */
    private int memberId;
    /**
     * Name of some member.
     */
    private String memberName;
    /**
     * @param memberName setter for university member.
     */
    UniversityMember(String memberName) {
        this.memberName = memberName;
        this.memberId = ++numberOfMembers;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }
}

class Course {
    /**
     * Maximum capacity of the course (number of students that can be enrolled in the course).
     */
    private static final int CAPACITY = 3;
    /**
     * Total number of created courses (static field, common for all instances of the class).
     */
    private static int numberOfCourses = 0;
    /**
     * Unique identifier for the course.
     */
    private int courseId;
    /**
     * Name of the course.
     */
    private String courseName;
    /**
     * List of students enrolled in this course.
     */
    private List<Student> enrolledStudents;
    /**
     * Level of the course (BACHELOR or MASTER).
     */
    private CourseLevel courseLevel;

    /**
     * @param courseName - setter of courseName.
     * @param courseLevel - setter of courseLevel.
     */
    Course(String courseName, CourseLevel courseLevel) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.enrolledStudents = new ArrayList<>();
        this.courseId = ++numberOfCourses;
    }

    public boolean isFull() {
        return enrolledStudents.size() == CAPACITY;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    /**
     * @param courseLevel - setter of courseLevel
     */
    public void setCourseLevel(CourseLevel courseLevel) {
        this.courseLevel = courseLevel;
    }
}

class Student extends UniversityMember implements Enrollable {
    /**
     * Maximum number of courses a student can be enrolled in.
     */
    private static final int MAX_ENROLLMENT = 3;
    /**
     * List of courses in which the student is currently enrolled.
     */
    private List<Course> enrolledCourses;

    Student(String memberName) {
        super(memberName);
        this.enrolledCourses = new ArrayList<>();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public static int getMaxEnrollment() {
        return MAX_ENROLLMENT;
    }

    @Override
    public boolean drop(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public boolean enroll(Course course) {
        if (!enrolledCourses.contains(course) && enrolledCourses.size() < MAX_ENROLLMENT && !course.isFull()) {
            enrolledCourses.add(course);
            return true;
        }
        return false;
    }
}

class Professor extends UniversityMember {
    /**
     * Maximum number of courses a professor can be assigned to teach.
     */
    private static final int MAX_LOAD = 2;

    /**
     * List of courses assigned to the professor for teaching.
     */
    private List<Course> assignedCourses;

    Professor(String memberName) {
        super(memberName);
        this.assignedCourses = new ArrayList<>();
    }

    public boolean teach(Course course) {
        if (!assignedCourses.contains(course) && assignedCourses.size() < MAX_LOAD) {
            assignedCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean exempt(Course course) {
        if (assignedCourses.contains(course)) {
            assignedCourses.remove(course);
            return true;
        }
        return false;
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public boolean isTeaching(Course course) {
        return assignedCourses.contains(course);
    }

    public static int getMaxLoad() {
        return MAX_LOAD;
    }
}
