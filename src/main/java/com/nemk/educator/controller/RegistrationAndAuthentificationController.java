package com.nemk.educator.controller;

import com.nemk.educator.model.User;
import com.nemk.educator.repository.UserRepository;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class RegistrationAndAuthentificationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Value("${upload.file.storage}")
    private String pathToStorage;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        if (bindingResult.hasErrors()){
            model.addAttribute("title" ,"Sign Up page");
            model.addAttribute("user" ,user);
            return "signup";
        }

        if (userService.isUserPresent(user.getEmail())){
            model.addAttribute("exist", true);
            return "signup";
        }
        Path path = Paths.get(pathToStorage + user.getUserName());
        File file = path.toFile();
        file.mkdir();
        Path pathAndCourses = Paths.get(path.toString(), "/courses");
        File file1 = pathAndCourses.toFile();
        file1.mkdir();

        model.addAttribute("message", "Registration success!! Sign in to continue");
        this.userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        return "redirect:/";
    }
}
