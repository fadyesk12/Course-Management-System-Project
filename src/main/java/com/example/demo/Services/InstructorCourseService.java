package com.example.demo.Services;

import com.example.demo.Model.*;
import com.example.demo.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorCourseService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public InstructorCourseService(InstructorRepository instructorRepository, CourseRepository courseRepository, LessonRepository lessonRepository, StudentRepository studentRepository, QuizRepository quizRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.quizRepository = quizRepository;
    }


    public void createCourse(Long instructorId, Course course) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalStateException("Instructor not found."));
        if (instructor.getCreatedCourses().contains(course)) {
            throw new IllegalStateException("Instructor already created this course.");
        }
        course.setInstructor(instructor);
        instructor.getCreatedCourses().add(course);
        instructorRepository.save(instructor);
        courseRepository.save(course);
    }

//    public int getQuizGrade(Long courseId, Long studentId, Long quizId) {
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new IllegalStateException("Course not found."));
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException("Student not found."));
//        Quiz quiz = quizRepository.findById(quizId)
//                .orElseThrow(() -> new IllegalStateException("Quiz not found."));
//
//        QuizSubmission quizSubmission = quiz.getQuizSubmissions().get(student);
//        if (!course.getStudents().contains(studentRepository.findById(studentId).   orElseThrow(() -> new IllegalStateException("Student not found."))) {
//            throw new IllegalStateException("Student not enrolled in this course.");
//        }
//        return course.getQuizzes().get(quizId).getGrade();
//    }

}