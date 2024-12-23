package com.example.demo.Services;

import com.example.demo.Model.Assignment;
import com.example.demo.Repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    // Method to create an assignment
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    public Assignment updateAssignment(Long assignmentId, Assignment updatedAssignment) {
        Optional<Assignment> existingAssignment = assignmentRepository.findById(assignmentId);

        if (existingAssignment.isPresent()) {
            Assignment assignment = existingAssignment.get();
            assignment.setTitle(updatedAssignment.getTitle());
            assignment.setDescription(updatedAssignment.getDescription());
            assignment.setSubmissionDeadline(updatedAssignment.getSubmissionDeadline());
            assignment.setCourse(updatedAssignment.getCourse());
            assignment.setInstructor(updatedAssignment.getInstructor());
            assignment.setStudent(updatedAssignment.getStudent());

            return assignmentRepository.save(assignment);
        } else {
            return null;
        }
    }
}
