package com.nemk.educator.controller;

import com.nemk.educator.model.*;
import com.nemk.educator.service.CourseService;
import com.nemk.educator.service.TaskService;
import com.nemk.educator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TaskService taskService;

    @Value("${upload.file.storage}")
    private String pathToStorage;

    @GetMapping("/{username}")
    public String authorizedPage(@PathVariable String username, Model model){
        User user = this.userService.findOne(username);
        model.addAttribute("user" ,user );
        model.addAttribute("username" ,user.getUserName());
        model.addAttribute("courses" ,this.userService.getCoursesByUserName(user.getUserName()) );

        return "profile/profile";
    }
    @GetMapping("/{username}/newCourse")
    public String newCourse(@PathVariable String username, Model model ){
        Course course = new Course();
        User user = this.userService.findOne(username);

        AddCourseForm addForm = new AddCourseForm(user,course);

        model.addAttribute("title" ,"create new Course");
        model.addAttribute("form" , addForm);

        return "profile/addCourse";
    }

    @PostMapping ("/newCourse")
    public String newCourse(Model model , @ModelAttribute AddCourseForm addForm){
        Course course = addForm.getCourse();
        course.setUser(userService.findOne(addForm.getUsername()));

        this.courseService.createCourse(course);

        model.addAttribute("message", "Course successfully added");
        return "success";
    }

    @GetMapping("/{username}/courses/{courseId}/newTask")
    public String newTask(@PathVariable String courseId,@PathVariable String username, Model model ){

        Task task = new Task();
        Course course =this.courseService.findOne(courseId);

        AddTaskForm addForm = new AddTaskForm(course, task);

        model.addAttribute("title" ,"create new task");
        model.addAttribute("form" , addForm);

        return "profile/addTask";
    }

    @PostMapping ("/newTask")
    public String newTask(Model model , @ModelAttribute AddTaskForm addForm, @RequestParam("file") MultipartFile file){

        Task task = addForm.getTask();
        System.out.println(task.getUrl());

        task.setCourse(courseService.findOne(addForm.getCourseId()));

        try {
            Path path = Paths.get(pathToStorage, file.getOriginalFilename());
            path.toFile().createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }


        task.setUrl(this.pathToStorage + file.getOriginalFilename());


        this.taskService.createTask(task);

        return "redirect:/profile/"+ task.getCourse().getUser().getUserName() +"/courses/" +task.getCourse().getId();
    }

    @GetMapping("/{username}/courses/{courseId}")
    public String course(Model model, @PathVariable String username, @PathVariable String courseId){

        model.addAttribute("course", this.courseService.findOne(courseId));
        model.addAttribute("tasks", this.taskService.getTasksBycourseId(courseId));
        model.addAttribute("username", username);

        return "profile/course";
    }


    @DeleteMapping
    @RequestMapping(value = "/{username}/courses/{courseId}/delete")
    public String deleteCourse(Model model, @PathVariable String username, @PathVariable String courseId){
        this.courseService.deleteCourse(courseId);

        return "redirect:/profile/{username}";
    }

    @GetMapping("/{username}/courses/{courseId}/tasks/{taskId}")
    public String course(Model model, @PathVariable String username, @PathVariable String courseId, @PathVariable String taskId){
        Task task = this.taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("courseId", courseId);
        model.addAttribute("taskId", taskId);
        model.addAttribute("username", username);

        return "profile/task";
    }

    @DeleteMapping("/{username}/courses/{courseId}/tasks/{taskId}/delete")
    public String deleteTask(Model model, @PathVariable String username, @PathVariable String courseId, @PathVariable String taskId){
        this.taskService.deleteTask(taskId);

        return "redirect:/profile/{username}/courses/{courseId}/tasks/{taskId}";
    }
}

//+ ${username} + '/courses/' + ${courseId} + '/tasks/' +${task.id}