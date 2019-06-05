package com.nemk.educator.service;

import com.nemk.educator.model.Task;
import com.nemk.educator.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;


    public void createTask(Task task) {
        this.taskRepository.save(task);
        System.out.println("task successfully added");
    }


    public List<Task> getTasksBycourseId(String courseId) {
        return this.taskRepository.findAllByCourse_Id(courseId);
    }

    public Task getTaskById(String taskId) {
        return this.taskRepository.findById(taskId).get();
    }

    public void deleteTask(String taskId) {
        System.out.println("deleting");
        this.taskRepository.deleteById(taskId);
    }
}

