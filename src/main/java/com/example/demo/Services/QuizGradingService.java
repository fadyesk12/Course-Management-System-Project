package com.example.demo.Services;

import com.example.demo.Model.QuizSubmission;
import com.example.demo.Model.Question;
import com.example.demo.Model.StudentAnswer;
import com.example.demo.Repositories.QuizSubmissionRepository;
import com.example.demo.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizGradingService {

    private final QuizSubmissionRepository quizSubmissionRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizGradingService(QuizSubmissionRepository quizSubmissionRepository, QuestionRepository questionRepository) {
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.questionRepository = questionRepository;
    }

    // Auto-grade a quiz submission
    public QuizSubmission gradeQuizSubmission(QuizSubmission quizSubmission) {

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
}
