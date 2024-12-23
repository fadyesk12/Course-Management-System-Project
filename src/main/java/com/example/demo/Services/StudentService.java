package com.example.demo.Services;

// import com.example.demo.Model.Course;
import com.example.demo.Model.*;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final StudentNotificationRepository studentNotificationRepository;

    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, LessonRepository lessonRepository,
                          StudentNotificationRepository studentNotificationRepository, CourseRepository courseRepository,
                          QuizRepository quizRepository, QuizSubmissionRepository quizSubmissionRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.studentNotificationRepository = studentNotificationRepository;
        this.courseRepository = courseRepository;
        this.quizRepository = quizRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
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


    public List<Lesson> RetrieveLessons(Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    public List<StudentNotification> retrieveNotifications(Long studentId) {
        return studentNotificationRepository.findByStudentId(studentId);
    }

    public void attendLesson(Long studentId, Long lessonId, Long otp, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("Lesson not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));
        if (!student.getAttendedLessons().contains(lesson)) {
            if (!lesson.getOTP().equals(otp)) {
                throw new IllegalStateException("Invalid OTP.");
            }
        }
        if (!lesson.getCourse().getId().equals(courseId)) {
            throw new IllegalStateException("Lesson not found in course.");
        }
        student.getAttendedLessons().add(lesson);
        studentRepository.save(student);
    }



//    public void submitQuiz(Long studentId, Long quizId, QuizSubmission quizSubmission) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException("Student not found."));
//        quizSubmission.setQuiz(quizRepository.findById(quizId)
//                .orElseThrow(() -> new IllegalStateException("Quiz not found.")));
//        if (!quizSubmission.getQuiz().getCourse().getEnrolledStudents().contains(student)) {
//            throw new IllegalStateException("Student not enrolled in course.");
//        }
//        quizSubmission.setStudent(student);
//        quizSubmission.setSubmissionDate(LocalDateTime.now());
//        student.getQuizSubmissions().add(quizSubmission);
//        studentRepository.save(student);
//        quizSubmissionRepository.save(quizSubmission);
//    }

    public void submitQuiz(Long studentId, Long quizId,List<StudentAnswer> answers) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalStateException("Quiz not found."));
        if (!quiz.getCourse().getEnrolledStudents().contains(student)) {
            throw new IllegalStateException("Student not enrolled in course.");
        }

        int grade = 0;

        List<Question> questions = quiz.getQuestions();

        for (Question question : questions) {
            for (StudentAnswer answer : answers) {
                if (answer.getQuestion().getId().equals(question.getId())) {
                    if (answer.getText().equalsIgnoreCase(question.getCorrectAnswer())) {
                        grade += 1;
                    }
                    break;
                }
            }
        }

        quizSubmissionRepository.save(new QuizSubmission(student, quiz, grade));

    }


}