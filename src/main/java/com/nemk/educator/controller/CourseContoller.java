package com.nemk.educator.controller;

import com.nemk.educator.model.Course;
import com.nemk.educator.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseContoller {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @RequestMapping("{id}")
    public String coursePage(@PathVariable String id, Model model){

        Course course = this.courseRepository.findById(id).get();

        model.addAttribute("title" ,course.getTitle());
        model.addAttribute("course", course);

        return "course/course_page";
    }

    @GetMapping
    public String courses( Model model){

        List<Course> courses = this.courseRepository.findAll();

        model.addAttribute("title" ,"All Courses: ");
        model.addAttribute("courses", courses);

        return "course/courses";
    }


//    @GetMapping
//    @RequestMapping("/byUser/{id}")
//    public String coursesByUserId(@PathVariable String id, Model model){
//
//        List<Course> courses = this.courseRepository.findAllByUserId(id);
//
//        model.addAttribute("title" ,"All Users");
//        model.addAttribute("courses", courses);
//
//        return "course/courses";
//    }

}
