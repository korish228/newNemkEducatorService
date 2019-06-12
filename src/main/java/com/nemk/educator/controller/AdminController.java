package com.nemk.educator.controller;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.Task;
import com.nemk.educator.model.User;
import com.nemk.educator.model.forms.AddCourseForm;
import com.nemk.educator.model.forms.AddTaskForm;
import com.nemk.educator.repository.UserRepository;
import com.nemk.educator.service.CourseService;
import com.nemk.educator.service.TaskService;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TaskService taskService;

    @GetMapping
    private String adminHome(Model model, Principal principal){
        User user = this.userService.findOne(principal.getName());

        model.addAttribute("user" ,user );
        model.addAttribute("username" ,user.getUserName());
        model.addAttribute("courses" ,this.userService.getCoursesByUserName(user.getUserName()) );

        return "admin/profile";
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    private String adminManage(Model model, Principal principal){
        User user = this.userService.findOne(principal.getName());

        model.addAttribute("user" ,user );
        model.addAttribute("users" ,this.userService.findAll());
        return "admin/manage";
    }



    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(Model model, @PathVariable String userId){
        this.userRepository.deleteById(userId);
        return "redirect:/";
    }


}