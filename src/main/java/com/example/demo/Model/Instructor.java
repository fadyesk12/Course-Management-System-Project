package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.*;

@NoArgsConstructor
@Entity
@Table(name = "instructor")
public class Instructor extends User {
    @Id
    @SequenceGenerator(
            name = "instructor_sequence",
            sequenceName = "instructor_sequence",
            initialValue = 77770001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructor_sequence"
    )
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String password;
    @Getter
    private String specialization;
    @Getter
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Course> createdCourses = new HashSet<>();
    //    private Map<Course, List<Question>> questionBanks;
    @Getter
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InstructorNotification> notifications;

    public Instructor(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
//                ", createdCourses=" + createdCourses + '\'' +
//                ", questionBanks=" + questionBanks + '\'' +
//                ", notifications=" + notifications + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor instructor = (Instructor) o;
        return Objects.equals(id, instructor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonProperty("createdCourses")
    public List<String> getCourseTitles() {
        try {
            List<String> courseTitles = new ArrayList<>();
            for (Course course : createdCourses) {
                courseTitles.add(course.getTitle());
            }
            return courseTitles;
        } catch (NullPointerException e) {
            return null;
        }
    }
}