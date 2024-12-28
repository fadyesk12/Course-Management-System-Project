package com.example.demo;

import com.example.demo.Controllers.StudentContoller;
import com.example.demo.Model.Student;
import com.example.demo.Model.StudentNotification;
import com.example.demo.Repositories.StudentNotificationRepository;
import com.example.demo.Repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentContoller studentContoller;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentNotificationRepository studentNotificationRepository;

    @Test
    void testGetAllStudents() {
        Student student1 = new Student("fady", "fady@example.com", "password123");
        Student student2 = new Student("karim", "karim@example.com", "password456");
        studentRepository.save(student1);
        studentRepository.save(student2);
        List<Student> students = studentContoller.getAllStudents().getBody();
        assertNotNull(students);
        assertTrue(students.size() >= 2);
    }

    @Test
    void testRetrieveStudentNotifications() {

        Student student = new Student("ahmed", "ahmed@example.com", "pass");
        studentRepository.save(student);

        StudentNotification notification1 = new StudentNotification();
        notification1.setId(1L);
        notification1.setMessage("Assignment");
        notification1.setStudent(student);

        StudentNotification notification2 = new StudentNotification();
        notification2.setId(2L);
        notification2.setMessage("Lesson");
        notification2.setStudent(student);
        studentNotificationRepository.save(notification1);
        studentNotificationRepository.save(notification2);
        List<StudentNotification> notifications = studentContoller.retrievStudentNotifications(student.getId()).getBody();
        assertNotNull(notifications);
        assertEquals(2, notifications.size());
        assertEquals("Assignment", notifications.get(0).getMessage());
        assertEquals("Lesson", notifications.get(1).getMessage());
    }

}
