package com.example.demo.Services;

// import com.example.demo.Model.Course;
import com.example.demo.Model.Course;
import com.example.demo.Model.Student;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void registerStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new IllegalStateException("Student with this email already exists.");
        }
        studentRepository.save(student);
    }

    public Student login(String email, String password) {
        return studentRepository.findByEmail(email)
                .filter(student -> student.getPassword().equals(password))
                .orElseThrow(() -> new IllegalStateException("Invalid email or password."));
    }

    public List<Student> getAllStudents() {
        if (studentRepository.findAll().isEmpty()) {
            throw new IllegalStateException("No students found.");
        }
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalStateException("Student not found.");
        }
        studentRepository.deleteById(id);
    }

}
