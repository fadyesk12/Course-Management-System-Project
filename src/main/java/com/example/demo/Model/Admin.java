package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import java.time.Year;

@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin extends User {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            initialValue = 66660001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String password;
//    private List<User> managedUsers;
//    private List<Course> managedCourses;
//    private List<Notification> notifications;

    public Admin(Long id, String name, String email, String password) {
        super(id, name, email, password);
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }
    public Admin(String name, String email, String password) {
        super(name, email, password);
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public String toString() {
        return "Admin{"
                +"id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
//                ", managedUsers=" + managedUsers + '\n'+
//                ", managedCourses=" + managedCourses + '\n'+
//                ", notifications=" + notifications + '\n' +
                "} "
                ;
    }
}
