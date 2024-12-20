package com.example.demo.Controllers;

import com.example.demo.Model.Instructor;
import com.example.demo.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/instructor")
public class InstructorContoller {
    private final InstructorService instructorService;

    @Autowired
    public InstructorContoller(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping("/all")
    public List<Instructor> getAllInstructors() {
        try {
            return instructorService.getAllInstructors();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/register")
    public void registerInstructor(@RequestBody Instructor instructor) {
        try {
            instructorService.registerInstructor(instructor);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
