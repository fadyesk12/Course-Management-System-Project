package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.Notification;
import java.time.Year;
import java.util.*;

@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends User {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            initialValue = 20220001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Getter
    @Column(name = "student_id")
    private Long id;
    @Getter
    @Column(name = "student_name")
    private String name;
    @Getter
    @Column(name = "student_email")
    private String email;
    @Getter
    @Column(name = "student_password")
    private String password;

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_enrolled_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private Set<Course> enrolledCourses = new HashSet<>();


    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_attended_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    @JsonIgnore
    private Set<Lesson> attendedLessons;
//    private Map<String, Double> quizScores;

    @Getter
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AssignmentSubmission> assignmentsSubmitted;


//    private Map<Assignment, Double> assignmentGrades;

    @Getter
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentNotification> notifications;

    public Student(Long id, String name, String email, String password)  {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Student(String name, String email, String password)  {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
//                ", enrolledCourses=" + enrolledCourses +
//                ", quizScores=" + quizScores +
//                ", assignmentsSubmitted=" + assignmentsSubmitted +
//                ", assignmentGrades=" + assignmentGrades +
//                ", notifications=" + notifications +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToMany(mappedBy = "students",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    private Set<Quiz> quizzes;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAnswer> studentAnswers;

    @JsonProperty("enrolledCourses")
    public List<String> getEnrolledCoursesTitle() {
        try {
            List<String> enrolledCoursesTitle = new ArrayList<>();
            for (Course course : enrolledCourses) {
                enrolledCoursesTitle.add(course.getTitle());
            }
            return enrolledCoursesTitle;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("attendedLessons")
    public List<String> getAttendedLessonsTitle() {
        try {
            List<String> attendedLessonsTitle = new ArrayList<>();
            for (Lesson lesson : attendedLessons) {
                attendedLessonsTitle.add(lesson.getTitle());
            }
            return attendedLessonsTitle;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("assignmentsSubmitted")
    public List<String> getAssignmentsSubmittedPaths() {
        try {
            List<String> assignmentsSubmittedIds = new ArrayList<>();
            for (AssignmentSubmission assignmentSubmission : assignmentsSubmitted) {
                assignmentsSubmittedIds.add(assignmentSubmission.getSubmissionPath());
            }
            return assignmentsSubmittedIds;
        } catch (NullPointerException e) {
            return null;
        }
    }
}