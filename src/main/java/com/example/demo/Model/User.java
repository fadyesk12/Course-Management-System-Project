package com.example.demo.Model;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String name;

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String password;

    public User() {
    }
=======
public abstract class User {
    private Long id;
    private String name;
    private String email;
    private String password;

    public User() {}
>>>>>>> 987585c7f22c2279dded27947a9273ac9e1ca2e4

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
<<<<<<< HEAD

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
=======
    public User( String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
>>>>>>> 987585c7f22c2279dded27947a9273ac9e1ca2e4
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

<<<<<<< HEAD
=======
    public abstract String getRole();

    public abstract String toString();
>>>>>>> 987585c7f22c2279dded27947a9273ac9e1ca2e4
}
