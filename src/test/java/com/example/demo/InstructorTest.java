package com.example.demo;

import com.example.demo.Controllers.InstructorController;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.InstructorNotification;
import com.example.demo.Repositories.InstructorRepository;
import com.example.demo.Repositories.InstructorNotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InstructorTest {

    @Autowired
    private InstructorController instructorController;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorNotificationRepository instructorNotificationRepository;

    @Test
    void testRetrieveInstructorNotifications() {
        Instructor instructor = new Instructor(1L, "fady","fady@example.com" ,"747483");
        instructorRepository.save(instructor);
        InstructorNotification notification1 = new InstructorNotification("course soon", instructor);
        InstructorNotification notification2 = new InstructorNotification("students came", instructor);
        instructorNotificationRepository.save(notification1);
        instructorNotificationRepository.save(notification2);
        List<InstructorNotification> notifications = instructorController.retrieveInstructorNotifications(instructor.getId()).getBody();
        assertNotNull(notifications);
        assertEquals(2, notifications.size());
        assertEquals("course soon", notifications.get(0).getMessage());
        assertEquals("students came", notifications.get(1).getMessage());
    }
}
