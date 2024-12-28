package com.example.demo.Services;

// import com.example.demo.Model.Course;
import com.example.demo.Model.*;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final StudentNotificationRepository studentNotificationRepository;
    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, LessonRepository lessonRepository,
                          StudentNotificationRepository studentNotificationRepository, AssignmentRepository assignmentRepository, CourseRepository courseRepository,
                          QuizRepository quizRepository, QuizSubmissionRepository quizSubmissionRepository, AssignmentSubmissionRepository assignmentSubmissionRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.studentNotificationRepository = studentNotificationRepository;
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.quizRepository = quizRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
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


    public List<Lesson> RetrieveLessons(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));

        List<Lesson> lessons = student.getAttendedLessons().stream().filter(lesson -> lesson.getCourse().getId().equals(courseId)).toList();
        try {
            if (lessons.isEmpty()) {
                throw new IllegalStateException("No lessons found.");
            }
            return lessons;
        } catch (Exception e) {
            throw new IllegalStateException("No lessons found.");
        }
    }

    public List<StudentNotification> retrieveNotifications(Long studentId) {
        List<StudentNotification> notifications = studentNotificationRepository.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalStateException("No notifications found."));
        for (StudentNotification notification : notifications) {
            notification.setRead(true);
        }
        return studentNotificationRepository.saveAll(notifications);
    }

    public Lesson attendLesson(Long studentId, Long lessonId, Long otp, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("Lesson not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));
        if (!student.getAttendedLessons().contains(lesson)) {
            if (!lesson.getOTP().equals(otp)) {
                System.out.println(lesson.getOTP());
                System.out.println(otp);
                throw new IllegalStateException("Invalid OTP.");
            }
        }
        if (!lesson.getCourse().getId().equals(courseId)) {
            throw new IllegalStateException("Lesson not found in course.");
        }
        student.getAttendedLessons().add(lesson);
        studentRepository.save(student);
        return lesson;
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

    private QuizSubmission submitQuizHelper(Long studentId, Long quizId,List<StudentAnswer> answers) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalStateException("Quiz not found."));
        if (!quiz.getCourse().getEnrolledStudents().contains(student)) {
            throw new IllegalStateException("Student not enrolled in course.");
        }

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setStudent(student);
        quizSubmission.setQuiz(quiz);
        quizSubmission.setSubmissionDate(LocalDateTime.now());
        quizSubmission.setAnswers(answers);
        quizSubmissionRepository.save(quizSubmission);
        return quizSubmission;

    }
    private QuizSubmission gradeQuizHelper(QuizSubmission quizSubmission) {

        List<StudentAnswer> studentAnswers = quizSubmission.getAnswers();
        int correctAnswers = 0;
        List<Question> questions = quizSubmission.getQuiz().getQuestions();

        for (Question question : questions) {
            for (StudentAnswer answer : studentAnswers) {
                if (answer.getQuestion().getId().equals(question.getId())) {
                    if (answer.getText().equalsIgnoreCase(question.getCorrectAnswer())) {
                        correctAnswers += 1;
                    }
                    break;
                }
            }
        }

        double grade = (double) correctAnswers / questions.size() * 100;
        quizSubmission.setGrade(grade);
        return quizSubmissionRepository.save(quizSubmission);
    }

    public QuizSubmission submitQuiz(Long studentId, Long quizId,List<StudentAnswer> answers) {
        QuizSubmission quizSubmission = submitQuizHelper(studentId, quizId,answers);
        gradeQuizHelper(quizSubmission);
        return quizSubmission;

    }


    public AssignmentSubmission submitAssignment(Long studentId, Long assignmentId, String fileName) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));

        try {
            Assignment assignment = assignmentRepository.findById(assignmentId)
                    .orElseThrow(() -> new IllegalStateException("Assignment not found."));
            if (!assignment.getCourse().getEnrolledStudents().contains(student)) {
                throw new IllegalStateException("Student not enrolled in course.");
            }
            AssignmentSubmission assignmentSubmission = new AssignmentSubmission();
            assignmentSubmission.setStudent(student);
            assignmentSubmission.setAssignment(assignment);
            assignmentSubmission.setSubmitted_date(LocalDateTime.now());
            assignmentSubmission.setSubmissionPath(fileName);
            return assignmentSubmissionRepository.save(assignmentSubmission);
        } catch (Exception e) {
            throw new IllegalStateException("Assignment not found.");
        }
        }




}