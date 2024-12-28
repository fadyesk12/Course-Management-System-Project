//package com.example.demo.Controllers;
//
//
//import com.example.demo.Model.Course;
//import com.example.demo.Model.Lesson;
//import com.example.demo.Services.LessonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class LessonController {
//    private final LessonService lessonService;
//    @Autowired
//    public LessonController(LessonService lessonService){
//        this.lessonService = lessonService;
//    }
//    @PostMapping("/addLesson/{course_ID}")
//    public void AddLesson(@PathVariable("course_ID") Long courseID, @RequestBody Lesson lesson){
//        lessonService.AddLesson(courseID, lesson);
//    }
//    @DeleteMapping("/deleteLesson/{course_ID}/{lesson_ID}")
//    public void deleteLesson(@PathVariable("course_ID") Long courseID, @PathVariable("lesson_ID") Long lessonID){
//        lessonService.deleteLesson(courseID, lessonID);
//    }
//    @PostMapping("/editLesson/{course_ID}/{lesson_ID}")
//    public void editLesson(@PathVariable("course_ID") Long courseID, @PathVariable("lesson_ID") Long lessonID, @RequestBody Lesson lesson){
//        lessonService.editLesson(courseID, lessonID, lesson);
//    }
//    @GetMapping("/getLessons")
//    public List<Lesson> getLessons(@RequestBody Course course){
//        return course.getLessons();
//    }
//}
