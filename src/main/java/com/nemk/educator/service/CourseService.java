package com.nemk.educator.service;

import com.nemk.educator.model.Course;
import com.nemk.educator.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    public List<Course> findAllByTitle(String name) {
        return this.courseRepository.findAllByTitleLike("%" + name + "%");
    }


    public Course findOne(String courseId) {
        return this.courseRepository.findById(courseId).get();
    }

    public Course createCourse(Course course) {
        return this.courseRepository.save(course);
    }

    public void deleteCourse(String courseId) {
        this.courseRepository.deleteById(courseId);
    }
}
