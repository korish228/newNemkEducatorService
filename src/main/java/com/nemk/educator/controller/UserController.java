package com.nemk.educator.controller;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.User;
import com.nemk.educator.repository.UserRepository;
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

    @GetMapping
    public String users(Model model, @RequestParam(defaultValue = "") String name){
        model.addAttribute("users", this.userService.findByEmailOrUserName(name));
        return "user/users";
    }

}
