package com.example.demo.Model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String duration;
    private String creationDate;
    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> enrolledStudents = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    @JsonProperty("instructorName")
    public String getInstructorName() {
        return instructor != null ? instructor.getName() : null;
    }

    public Course(Long id, String title, String description, String duration, String creationDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
    }

    public Course(Long id, String title, String description){
        this.title = title;
        this.description = description;
    }

    public Course(Long id, String title, String description, String duration, String creationDate, Instructor instructor){
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonProperty("enrolledStudents")
    public List<Long> getEnrolledStudentIds() {
        try {
            List<Long> studentIds = new ArrayList<>();
            for (Student student : enrolledStudents) {
                studentIds.add(student.getId());
            }
            return studentIds;
        } catch (Exception e) {
            return null;
        }
    }

    @JsonProperty("lessons")
    public List<String> getLessonTitles() {
        try {
            List<String> lessonTitles = new ArrayList<>();
            for (Lesson lesson : lessons) {
                lessonTitles.add(lesson.getTitle());
            }
            return lessonTitles;
        } catch (Exception e) {
            return null;
        }
    }

    @JsonProperty("quizzes")
    public List<Long> getQuizIds() {
        try {
            List<Long> quizIds = new ArrayList<>();
            for (Quiz quiz : quizzes) {
                quizIds.add(quiz.getId());
            }
            return quizIds;
        } catch (Exception e) {
            return null;
        }
    }

    @JsonProperty("assignments")
    public List<Long> getAssignmentIds() {
        try {
            List<Long> assignmentIds = new ArrayList<>();
            for (Assignment assignment : assignments) {
                assignmentIds.add(assignment.getId());
            }
            return assignmentIds;
        } catch (Exception e) {
            return null;
        }
    }


}