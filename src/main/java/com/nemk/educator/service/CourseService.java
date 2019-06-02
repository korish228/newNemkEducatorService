package com.nemk.educator.service;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.Role;
import com.nemk.educator.model.User;
import com.nemk.educator.repository.CourseRepository;
import com.nemk.educator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
