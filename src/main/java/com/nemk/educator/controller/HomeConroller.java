package com.nemk.educator.controller;

import com.nemk.educator.model.User;
import com.nemk.educator.repository.UserRepository;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeConroller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String home(Model model){
        model.addAttribute("title" ,"Home page");
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(Model model){

        model.addAttribute("title" ,"Login page");
        return "login";
    }

    @GetMapping(value = "/signup")
    public String signUp(Model model){
        User user = new User();

        model.addAttribute("title" ,"Sign Up page");
        model.addAttribute("user" ,user);
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(Model model, @ModelAttribute @Valid User user , BindingResult bindingResult){

        System.out.println(user);
        if (bindingResult.hasErrors()){
            model.addAttribute("title" ,"Sign Up page");
            model.addAttribute("user" ,user);
            return "signup";
        }
        if (userService.isUserPresent(user.getEmail())){
            model.addAttribute("exist", true);
            return "signup";
        }


        this.userService.createUser(user);
        return "success";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        return "redirect:/";
    }
}
