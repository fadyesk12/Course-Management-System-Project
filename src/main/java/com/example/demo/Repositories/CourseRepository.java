package com.example.demo.Repositories;

import com.example.demo.Model.Course;
// import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.Optional;
// import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,String>{
}