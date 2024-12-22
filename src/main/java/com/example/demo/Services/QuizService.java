package com.example.demo.Services;

import com.example.demo.Model.*;
import com.example.demo.Repositories.AnswerRepository;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.QuestionRepository;
import com.example.demo.Repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    private final CourseRepository courseRepository;

    private final AnswerRepository answerRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository , QuestionRepository questionRepository,
                       CourseRepository courseRepository, AnswerRepository answerRepository){
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.courseRepository = courseRepository;
        this.answerRepository = answerRepository;
    }

    public Quiz createQuiz(Long courseId, String title, int duration) {
        Quiz quiz = new Quiz();
        Course course = courseRepository.findById(courseId).orElseThrow(()
                -> new RuntimeException("Course not found with id: " + courseId));
        quiz.setCourse(course);
        quiz.setTitle(title);
        quiz.setDuration(duration);
        return quizRepository.save(quiz);
    }

    public Question addQuestion(Long quizId, Question question, List<Answer> answers) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId)); // making sure the quiz already exists
        question.setQuiz(quiz);
        if (question.getType() == QuestionType.MCQ || question.getType() == QuestionType.TRUE_FALSE) {
            if (answers == null || answers.isEmpty()) {
                throw new RuntimeException("Options must be provided for MCQ or True/False questions");
            }
        } else if (question.getType() == QuestionType.SHORT_ANSWER) {
            if (question.getCorrectAnswer() == null || question.getCorrectAnswer().isEmpty()) {
                throw new RuntimeException("Correct answer must be provided for Short Answer questions");
            }
        }
        for (Answer answer : answers) {
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
        return questionRepository.save(question);
    }

    private List<Question> randomizingQuestions(List<Question> questions) {
        Random random = new Random();
        List<Question> shuffledQuestions = new ArrayList<>(questions);
        for (int i = 0; i < shuffledQuestions.size(); i++) {
            int randomIndex = random.nextInt(shuffledQuestions.size());
            Question temp = shuffledQuestions.get(i);
            shuffledQuestions.set(i, shuffledQuestions.get(randomIndex));
            shuffledQuestions.set(randomIndex, temp);
        }
        return shuffledQuestions;
    }


    public List<Question> getRandomQuestionsForQuiz(Long quizId, int numQuestions) {
        List<Question> allQuestions = questionRepository.findByQuizId(quizId);
        if (allQuestions.size() < numQuestions) {
            throw new RuntimeException("No questions available for the quiz");
        }
        List<Question> shuffledQuestions = randomizingQuestions(allQuestions);
        List<Question> randomQuestions = new ArrayList<>();
        for (int i = 0; i < numQuestions; i++) {
            randomQuestions.add(shuffledQuestions.get(i));
        }
        return randomQuestions;
    }

    public List<Quiz> getQuizzesByCourse(Long courseId) {
        return quizRepository.findByCourseId(courseId);
    }

    public Quiz getQuizById(long quizId){
        return quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }
}

