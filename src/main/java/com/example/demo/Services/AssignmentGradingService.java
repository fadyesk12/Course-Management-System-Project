package com.example.demo.Services;

import com.example.demo.Model.AssignmentSubmission;
import com.example.demo.Repositories.AssignmentSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentGradingService {

    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Autowired
    public AssignmentGradingService(AssignmentSubmissionRepository assignmentSubmissionRepository) {
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
    }

    public AssignmentSubmission gradeAssignment(Long submissionId, long grade) {
        Optional<AssignmentSubmission> assignmentSubmission = assignmentSubmissionRepository.findById(submissionId);
        
        if (assignmentSubmission.isPresent()) {
            AssignmentSubmission submission = assignmentSubmission.get();
            submission.setGrade(grade);  
            return assignmentSubmissionRepository.save(submission);  
        } else {
            return null; 
        }
    }

}
