package com.nemk.educator.controller;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.User;
import com.nemk.educator.service.CourseService;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/{username}")
    public String authorizedPage(@PathVariable String username, Model model){
        User user = this.userService.findOne(username);
        model.addAttribute("user" ,user );
        model.addAttribute("username" ,user.getUserName());
        model.addAttribute("courses" ,this.userService.getCoursesByUserName(user.getUserName()) );

        return "profile/profile";
    }

    @GetMapping("/newCourse/{username}")
    public String newCourse(@PathVariable String username, Model model ){
        Course course = new Course();

        System.out.println(username);
//        User user = this.userService.findOne(username);

        model.addAttribute("title" ,"create new Course");
//        model.addAttribute("user" , user);
        model.addAttribute("course" , course);
        return "profile/addCourse";
    }

    @PostMapping ("/newCourse")
    public String newCourse(Model model , Course course){

        System.out.println(course);

        return "profile/addCourse";
    }

    @GetMapping("/{username}/courses/{courseId}")
    public String course(Model model, @PathVariable String username, @PathVariable String courseId){

        model.addAttribute("course", this.courseService.findOne(courseId));

        return "profile/course";
    }
}
