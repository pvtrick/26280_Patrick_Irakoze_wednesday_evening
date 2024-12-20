package ExercisesSecond;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

// Custom Exception for Invalid Attendance
class AttendanceException extends Exception {
    public AttendanceException(String message) {
        super(message);
    }
}

// Custom Exception for Invalid Student ID
class StudentIdException extends Exception {
    public StudentIdException(String message) {
        super(message);
    }
}

// Base Class: Individual
class Individual {
    private String fullName;
    private String studentId; // 6-digit numeric
    private String emailAddress;

    public Individual(String fullName, String studentId, String emailAddress) {
        setFullName(fullName);
        setStudentId(studentId);
        setEmailAddress(emailAddress);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (!isValidId(studentId)) {
            throw new IllegalArgumentException("ID must be numeric and exactly 6 digits.");
        }
        this.studentId = studentId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        if (!isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Email format is invalid.");
        }
        this.emailAddress = emailAddress;
    }

    private boolean isValidId(String id) {
        return id.matches("\\d{6}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}

// Class: Learner (Inherits from Individual)
class Learner extends Individual {
    private String gradeLevel;
    private String classSection;
    private double attendanceRate;

    public Learner(String fullName, String studentId, String emailAddress, String gradeLevel, String classSection) {
        super(fullName, studentId, emailAddress);
        this.gradeLevel = gradeLevel;
        this.classSection = classSection;
        this.attendanceRate = 0.0;
    }

    public void updateAttendance(int daysPresent, int totalDays) throws AttendanceException {
        if (daysPresent < 0 || totalDays <= 0 || daysPresent > totalDays) {
            throw new AttendanceException("Invalid attendance data.");
        }
        attendanceRate = (double) daysPresent / totalDays * 100;
    }

    public double getAttendanceRate() {
        return attendanceRate;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public String getClassSection() {
        return classSection;
    }
}

// Interface: AttendanceTracker
interface AttendanceTracker {
    void recordAttendance(String studentId, boolean isPresent) throws StudentIdException;
    double fetchAttendance(String studentId) throws StudentIdException;
    void displayAttendanceReport();
}

// Concrete Class: AttendanceManager (Implements AttendanceTracker)
class AttendanceManager implements AttendanceTracker {
    private Map<String, Learner> learnerMap;

    public AttendanceManager() {
        learnerMap = new HashMap<>();
    }

    public void addLearner(Learner learner) {
        learnerMap.put(learner.getStudentId(), learner);
    }

    @Override
    public void recordAttendance(String studentId, boolean isPresent) throws StudentIdException {
        if (!learnerMap.containsKey(studentId)) {
            throw new StudentIdException("Student ID does not exist.");
        }

        // Simulating attendance marking
        Learner learner = learnerMap.get(studentId);
        int totalDays = 30; // Assuming a total of 30 days
        int daysPresent = isPresent ? 1 : 0; // Simplification for demonstration

        try {
            learner.updateAttendance(daysPresent, totalDays);
        } catch (AttendanceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public double fetchAttendance(String studentId) throws StudentIdException {
        if (!learnerMap.containsKey(studentId)) {
            throw new StudentIdException("Student ID does not exist.");
        }
        return learnerMap.get(studentId).getAttendanceRate();
    }

    @Override
    public void displayAttendanceReport() {
        for (Learner learner : learnerMap.values()) {
            System.out.println("Student ID: " + learner.getStudentId() + ", Name: " + learner.getFullName() +
                    ", Attendance Rate: " + learner.getAttendanceRate() + "%");
        }
    }
}

// Main Class for User Input and Testing
public class StudentAttendanceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceManager attendanceManager = new AttendanceManager();

        // Adding learners based on user input
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter details for learner " + (i + 1) + ":");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Student ID (6 digits): ");
            String studentId = scanner.nextLine();

            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();

            System.out.print("Grade Level: ");
            String gradeLevel = scanner.nextLine();

            System.out.print("Class Section: ");
            String classSection = scanner.nextLine();

            try {
                Learner learner = new Learner(fullName, studentId, emailAddress, gradeLevel, classSection);
                attendanceManager.addLearner(learner);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                i--; // Decrement to retry this learner
            }
        }

        // Marking attendance based on user input
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter Student ID to mark attendance: ");
            String studentId = scanner.nextLine();

            System.out.print("Is the student present? (true/false): ");
            boolean isPresent = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline character

            try {
                attendanceManager.recordAttendance(studentId, isPresent);
            } catch (StudentIdException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Generate attendance report
        System.out.println("\nAttendance Report:");
        attendanceManager.displayAttendanceReport();

        scanner.close();
    }
}
