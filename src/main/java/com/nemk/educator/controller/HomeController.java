package com.nemk.educator.controller;

import com.nemk.educator.model.*;
import com.nemk.educator.model.forms.AddCourseForm;
import com.nemk.educator.model.forms.AddTaskForm;
import com.nemk.educator.repository.CourseRepository;
import com.nemk.educator.service.CourseService;
import com.nemk.educator.service.StorageService;
import com.nemk.educator.service.TaskService;
import com.nemk.educator.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
//@RequestMapping("/profile")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TaskService taskService;

    @Value("${upload.file.storage}")
    private String pathToStorage;

    @Autowired
    private StorageService storageService;


    @GetMapping
    public String homePage(Model model, Principal principal){



        if (principal == null){
            return "index";
        }

        User user = this.userService.findOne(principal.getName());

        if (user.getRole().equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }
        System.out.println(principal);

        model.addAttribute("user" ,user );
        model.addAttribute("username" ,user.getUserName());
        model.addAttribute("courses" ,this.userService.getCoursesByUserName(user.getUserName()) );

            return "profile/profile";
    }
    @GetMapping("/newCourse")
    public String newCourse(Principal principal, Model model ){
        Course course = new Course();
        User user = this.userService.findOne(principal.getName());

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

        Path path = Paths.get(pathToStorage,course.getUser().getUserName(), "/courses" , course.getTitle());
        File file = path.toFile();
        file.mkdir();

        Path pathAndCourses = Paths.get(path.toString(), "/tasks");
        File file1 = pathAndCourses.toFile();
        file1.mkdir();

        return "redirect:/";
    }

    @GetMapping("/current/courses/{courseId}")
    public String course(Model model, Principal principal, @PathVariable String courseId){

        model.addAttribute("course", this.courseService.findOne(courseId));
        model.addAttribute("tasks", this.taskService.getTasksBycourseId(courseId));
        model.addAttribute("username", principal.getName());

        return "profile/course";
    }




    @GetMapping("/current/courses/{courseId}/newTask")
    public String newTask(@PathVariable String courseId, Model model ){

        Task task = new Task();
        Course course =this.courseService.findOne(courseId);

        AddTaskForm addForm = new AddTaskForm(course, task);

        model.addAttribute("title" ,"create new task");
        model.addAttribute("form" , addForm);

        return "profile/addTask";
    }

    @PostMapping ("/newTask")
    public String newTask(Model model,Principal principal, @ModelAttribute AddTaskForm addForm, @RequestParam("file") MultipartFile file) throws IOException {

//        String extensions[] = {""};

        Task task = addForm.getTask();
        task.setCourse(courseService.findOne(addForm.getCourseId()));

        Path path = Paths.get(pathToStorage, task.getCourse().getUser().getUserName(), "/courses" , task.getCourse().getTitle(), "/tasks", task.getTitle());
        Path pathUrl = Paths.get(path.toString(),file.getOriginalFilename());
        task.setUrl(pathUrl.toString());

        path.toFile().mkdir();

        this.storageService.store(file, path);

        this.taskService.createTask(task);


        return "redirect:/current/courses/tasks/" +task.getCourse().getId();
    }

//    @RequestMapping(value = "/files/{taskId}")
//    @ResponseBody
//    public String getImage(@PathVariable("taskId") String taskId) throws IOException{
//
//        Task task = this.taskService.getTaskById(taskId);
//
//        File file = new File(task.getUrl());
//        System.out.println(file.getAbsolutePath());
//        return file.getAbsolutePath();
//    }

    @GetMapping(value = "/videosrc", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource videoSource(@RequestParam(value="taskId", required=true) String taskId) throws IOException, URISyntaxException {
        Task task = this.taskService.getTaskById(taskId);

        File file = new File(task.getUrl());

        FileSystemResource fileSystemResource = new FileSystemResource(file);

//        System.out.println(fileSystemResource.getURL().toURI().toString());

        return fileSystemResource;
    }

    @GetMapping("/current/courses/{courseId}/tasks/{taskId}")
    public String task(Model model, Principal principal, @PathVariable String courseId, @PathVariable String taskId){
        Task task = this.taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("courseId", courseId);
        model.addAttribute("taskId", taskId);
        model.addAttribute("username", principal.getName());

//        File video = new File(task.getUrl());

//        System.out.println(video.getAbsolutePath());

        model.addAttribute("courseId", task.getId());

        return "profile/task";
    }


    @RequestMapping("/current/courses/delete/{courseId}")
    public String deleteCourse(Model model, @PathVariable String courseId) throws IOException {
        Course course = this.courseRepository.getOne(courseId);

        this.courseRepository.deleteById(courseId);

        Path path = Paths.get(pathToStorage,course.getUser().getUserName(), "/courses" , course.getTitle());

        FileUtils.deleteDirectory(path.toFile());



        model.addAttribute("message", "Course was successfully deleted");
        return "redirect:/";
    }

    @RequestMapping("/current/courses/{courseId}/tasks/delete/{taskId}")
    public String deleteCourse(Model model, @PathVariable String courseId, @PathVariable String taskId) throws IOException {

        Task task = this.taskService.getTaskById(taskId);

        this.taskService.deleteTask(taskId);

        Path path = Paths.get(pathToStorage, task.getCourse().getUser().getUserName(), "/courses" , task.getCourse().getTitle(), "/tasks", task.getTitle());
        FileUtils.deleteDirectory(path.toFile());
//        path.toFile().delete();

        model.addAttribute("message", "Course was successfully deleted");

        return "redirect:/";
    }



}