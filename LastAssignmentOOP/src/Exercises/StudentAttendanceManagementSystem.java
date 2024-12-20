package Exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// Custom Exception for Invalid Attendance
class InvalidAttendanceException extends Exception {
    public InvalidAttendanceException(String message) {
        super(message);
    }
}

// Custom Exception for Invalid Student ID
class InvalidStudentIdException extends Exception {
    public InvalidStudentIdException(String message) {
        super(message);
    }
}

// Base Class: Person
class Person {
    private String name;
    private String id; // 6-digit numeric
    private String email;

    public Person(String name, String id, String email) {
        setName(name);
        setId(id);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("ID must be numeric and exactly 6 digits.");
        }
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email format is invalid.");
        }
        this.email = email;
    }

    private boolean isValidId(String id) {
        return id.matches("\\d{6}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}

// Class: Student (Inherits from Person)
class Student extends Person {
    private String grade;
    private String section;
    private double attendancePercentage;

    public Student(String name, String id, String email, String grade, String section) {
        super(name, id, email);
        this.grade = grade;
        this.section = section;
        this.attendancePercentage = 0.0;
    }

    public void updateAttendance(int daysPresent, int totalDays) throws InvalidAttendanceException {
        if (daysPresent < 0 || totalDays <= 0 || daysPresent > totalDays) {
            throw new InvalidAttendanceException("Invalid attendance data.");
        }
        attendancePercentage = (double) daysPresent / totalDays * 100;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public String getGrade() {
        return grade;
    }

    public String getSection() {
        return section;
    }
}

// Interface: IAttendanceManager
interface IAttendanceManager {
    void markAttendance(String studentId, boolean isPresent) throws InvalidStudentIdException;
    double getAttendance(String studentId) throws InvalidStudentIdException;
    void generateAttendanceReport();
}

// Concrete Class: AttendanceSystem (Implements IAttendanceManager)
class AttendanceSystem implements IAttendanceManager {
    private Map<String, Student> students;

    public AttendanceSystem() {
        students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public void markAttendance(String studentId, boolean isPresent) throws InvalidStudentIdException {
        if (!students.containsKey(studentId)) {
            throw new InvalidStudentIdException("Student ID does not exist.");
        }

        // Simulating attendance marking
        Student student = students.get(studentId);
        // Here we assume a simple case where we mark attendance for 30 days
        // In a real system, a more complex structure should be used to track daily attendance
        int totalDays = 30;
        int daysPresent = isPresent ? 1 : 0; // Simplification for demonstration

        try {
            student.updateAttendance(daysPresent, totalDays);
        } catch (InvalidAttendanceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public double getAttendance(String studentId) throws InvalidStudentIdException {
        if (!students.containsKey(studentId)) {
            throw new InvalidStudentIdException("Student ID does not exist.");
        }
        return students.get(studentId).getAttendancePercentage();
    }

    @Override
    public void generateAttendanceReport() {
        for (Student student : students.values()) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() +
                    ", Attendance Percentage: " + student.getAttendancePercentage() + "%");
        }
    }
}

// Main Class for Testing
public class StudentAttendanceManagementSystem {
    public static void main(String[] args) {
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        // Adding students
        try {
            Student student1 = new Student("Alice", "123456", "alice@example.com", "10", "A");
            Student student2 = new Student("Bob", "654321", "bob@example.com", "10", "B");
            Student student3 = new Student("Charlie", "112233", "charlie@example.com", "10", "C");

            attendanceSystem.addStudent(student1);
            attendanceSystem.addStudent(student2);
            attendanceSystem.addStudent(student3);

            // Mark attendance for each student
            attendanceSystem.markAttendance("123456", true);
            attendanceSystem.markAttendance("654321", false);
            attendanceSystem.markAttendance("112233", true);

            // Generate attendance report
            attendanceSystem.generateAttendanceReport();

            // Simulating invalid data input
            attendanceSystem.markAttendance("999999", true); // Invalid student ID
            student1.setEmail("invalid-email"); // Invalid email format

        } catch (IllegalArgumentException | InvalidStudentIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
