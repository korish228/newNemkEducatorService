package com.nemk.educator.controller;

import com.nemk.educator.model.User;
import com.nemk.educator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserContoller {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @RequestMapping("{id}")
    public String userPage(@PathVariable String id, Model model){

        User user = this.userRepository.findById(id).get();

        model.addAttribute("title" ,user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("courses", user.getCourses());

        return "user/user_page";
    }

    @GetMapping
    public String users(Model model){

        List<User> users = this.userRepository.findAll();

        model.addAttribute("title" ,"All Users");
        model.addAttribute("users", users);

        return "user/users";
    }

}
