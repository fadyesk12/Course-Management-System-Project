package com.example.demo;

import com.example.demo.Controllers.QuizController;
import com.example.demo.Model.Course;
import com.example.demo.Model.Question;
import com.example.demo.Model.Quiz;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.QuizRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuizTest {

    @Autowired
    private QuizController quizController;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testCreateQuiz() {
        Course course = new Course();
        course.setTitle("Sample Course");
        courseRepository.save(course);

        Quiz quizRequest = new Quiz();
        quizRequest.setTitle("Quiz 1");
        quizRequest.setDuration(30);
        Quiz createdQuiz = quizController.createQuiz(course.getId(), quizRequest).getBody();
        assertNotNull(createdQuiz);
        assertEquals("Quiz 1", createdQuiz.getTitle());
        assertEquals(30, createdQuiz.getDuration());
    }

    @Test
    void testGetQuizById() {
        Quiz newQuiz = new Quiz();
        newQuiz.setTitle("Sample Quiz");
        newQuiz.setDuration(45);
        quizRepository.save(newQuiz);

        Quiz retrievedQuiz = quizController.getQuizById(newQuiz.getId()).getBody();
        assertNotNull(retrievedQuiz);
        assertEquals(newQuiz.getId(), retrievedQuiz.getId());
        assertEquals("Sample Quiz", retrievedQuiz.getTitle());
        assertEquals(45, retrievedQuiz.getDuration());
    }

    @Test
    void testGetQuizzesByCourse() {
        Course course = new Course();
        course.setTitle("Sample Course");
        courseRepository.save(course);

        Quiz quiz1 = new Quiz();
        quiz1.setTitle("Quiz 1");
        quiz1.setDuration(20);
        quiz1.setCourse(course);
        quizRepository.save(quiz1);

        Quiz quiz2 = new Quiz();
        quiz2.setTitle("Quiz 2");
        quiz2.setDuration(30);
        quiz2.setCourse(course);
        quizRepository.save(quiz2);

        List<Quiz> quizzes = quizController.getQuizzesByCourse(course.getId()).getBody();

        assertNotNull(quizzes);
        assertEquals(2, quizzes.size());
    }

    @Test
    void testAddQuestion() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Sample Quiz");
        quiz.setDuration(60);
        quizRepository.save(quiz);

        Question question = new Question();
        question.setText("Sample Question");
        question.setCorrectAnswer("Option A");

        Question addedQuestion = quizController.addQuestion(quiz.getId(), question).getBody();

        assertNotNull(addedQuestion);
        assertEquals("Sample Question", addedQuestion.getText());
        assertEquals("Option A", addedQuestion.getCorrectAnswer());
    }
}
