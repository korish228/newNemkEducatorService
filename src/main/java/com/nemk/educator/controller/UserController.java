package com.nemk.educator.controller;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.User;
import com.nemk.educator.repository.UserRepository;
import com.nemk.educator.service.CourseService;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String users(Model model, @RequestParam(defaultValue = "") String name){
        model.addAttribute("users", this.userService.findByEmailOrUserName(name));
        return "user/users";
    }
    @GetMapping("{userName}")
    public String user(Model model, @PathVariable String userName){
        User user = this.userService.findOne(userName);
        model.addAttribute("user" ,user );
        model.addAttribute("username" ,user.getUserName());
        model.addAttribute("courses" ,this.userService.getCoursesByUserName(user.getUserName()) );
        return "user/current_user";
    }

    @GetMapping("/{userName}/courses/{courseId}")
    public String course(@PathVariable String courseId, @PathVariable String userName, Model model){

        Course course = this.courseService.findOne(courseId);

        model.addAttribute("course", course);
        model.addAttribute("title", course.getTitle());

        return "course/current_course";
    }

}
