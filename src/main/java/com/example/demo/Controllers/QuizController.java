package com.example.demo.Controllers;

import com.example.demo.Model.Question;
import com.example.demo.Model.Quiz;
import com.example.demo.Model.QuizSubmission;
import com.example.demo.Model.Student;
import com.example.demo.Services.QuizGradingService;
import com.example.demo.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Model.Answer;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuizGradingService quizGradingService;

    @Autowired
    public QuizController(QuizService quizService, QuizGradingService quizGradingService) {
        this.quizService = quizService;
        this.quizGradingService = quizGradingService;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<Quiz> createQuiz(@PathVariable Long courseId, @RequestBody Quiz quizRequest) {
        Quiz createdQuiz = quizService.createQuiz(courseId, quizRequest.getTitle(), quizRequest.getDuration());
        return ResponseEntity.status(201).body(createdQuiz);
    }

    @PostMapping("/{quizId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Long quizId, @RequestBody Question question) {
        try {
            System.out.println("Received question: " + question);
            System.out.println("Answers: " + question.getAnswers());
            Question createdQuestion = quizService.addQuestion(quizId, question);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("deleteQuestion/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        quizService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{quizId}/random-questions")
    public ResponseEntity<List<Question>> getRandomQuestions(@PathVariable Long quizId, @RequestParam int numQuestions) {
        try {
            List<Question> randomQuestions = quizService.getRandomQuestionsForQuiz(quizId, numQuestions);
            return ResponseEntity.ok(randomQuestions);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Quiz>> getQuizzesByCourse(@PathVariable Long courseId) {
        List<Quiz> quizzes = quizService.getQuizzesByCourse(courseId);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId) {
        Quiz quiz = quizService.getQuizById(quizId);
        return ResponseEntity.ok(quiz);
    }


    @PostMapping("/grade/{quizSubmissionId}")
    public ResponseEntity<QuizSubmission> gradeQuiz(@PathVariable Long quizSubmissionId) {
        QuizSubmission gradedSubmission = quizGradingService.gradeQuizSubmission(quizSubmissionId);
        return ResponseEntity.ok(gradedSubmission);
    }
}
