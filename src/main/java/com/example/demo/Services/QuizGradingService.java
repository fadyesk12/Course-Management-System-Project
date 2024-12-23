package com.example.demo.Services;

import com.example.demo.Model.QuizSubmission;
import com.example.demo.Model.Question;
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
    public QuizSubmission gradeQuizSubmission(Long quizSubmissionId) {
        QuizSubmission quizSubmission = quizSubmissionRepository.findById(quizSubmissionId)
                .orElseThrow(() -> new RuntimeException("Quiz Submission not found"));

        List<String> studentAnswers = quizSubmission.getAnswers();
        int correctAnswers = 0;
        List<Question> questions = questionRepository.findByQuizId(quizSubmission.getQuiz().getId());

        for (int i = 0; i < studentAnswers.size(); i++) {
            Question question = questions.get(i);
            String correctAnswer = question.getCorrectAnswer();

            if (studentAnswers.get(i).equalsIgnoreCase(correctAnswer)) {
                correctAnswers++;
            }
        }
        double grade = (double) correctAnswers / questions.size() * 100;
        quizSubmission.setGrade(grade);
        return quizSubmissionRepository.save(quizSubmission);
    }
}
