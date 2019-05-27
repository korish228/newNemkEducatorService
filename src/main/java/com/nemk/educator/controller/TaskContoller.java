package com.nemk.educator.controller;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.Task;
import com.nemk.educator.repository.CourseRepository;
import com.nemk.educator.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("tasks")
public class TaskContoller {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    @RequestMapping("{id}")
    public String taskPage(@PathVariable String id, Model model){

        Task task = this.taskRepository.findById(id).get();

        model.addAttribute("title" ,task.getTitle());
        model.addAttribute("course", task);

        return "task/task_page";
    }

    @GetMapping
    @RequestMapping("/byUser/{courseId}")
    public String tasksByUserId(@PathVariable String courseId, Model model){

        List<Course> courses = this.taskRepository.findAllByCourseId(courseId);

        model.addAttribute("title" ,"All Users");
        model.addAttribute("courses", courses);

        return "task/tasks";
    }

}
