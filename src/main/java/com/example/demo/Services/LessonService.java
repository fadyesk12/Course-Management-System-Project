package com.example.demo.Services;

import com.example.demo.Model.Course;
import com.example.demo.Model.Lesson;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    @Autowired
    LessonService(LessonRepository lessonRepository, CourseRepository courseRepository){
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }
    public Lesson AddLesson(Long courseId, Lesson lesson){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId)); // making sure the course already exists
        lesson.setCourse(course);
        lesson.setOTP((long) ((Math.random() * 1000000) % 1000000));
        course.getLessons().add(lesson);
        courseRepository.save(course);

        return lesson;
    }
    public void deleteLesson(Long courseId, Long lessonId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId)); // making sure the course already exists
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId)); // making sure the lesson already exists
        lessonRepository.delete(lesson);
        course.getLessons().remove(lesson);
        courseRepository.save(course);
    }
    public Lesson editLesson(Long courseId, Long lessonId, Lesson lesson){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId)); // making sure the course already exists
        Lesson lesson1 = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId)); // making sure the lesson already exists
        lesson1.setTitle(lesson.getTitle());
        lesson1.setDate(lesson.getDate());
        lessonRepository.save(lesson1);
        return lesson1;
    }
    public Lesson generateOTP(Long lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId)); // making sure the lesson already exists
        lesson.setOTP((long) ((Math.random() * 1000000) % 1000000));
        return lessonRepository.save(lesson);
    }

}