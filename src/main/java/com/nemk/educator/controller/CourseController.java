package com.nemk.educator.controller;

import com.nemk.educator.service.CourseService;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String courses(Model model, @RequestParam(defaultValue = "") String name){
        model.addAttribute("courses", this.courseService.findAllByTitle(name));
        return "course/courses";
    }



}
