package com.nemk.educator.model.forms;


import com.nemk.educator.model.Course;
import com.nemk.educator.model.Task;

public class AddTaskForm {

    private String courseId;

    private Course course;
    private Task task;

    public AddTaskForm(Course course, Task task) {
        this.course = course;
        this.task = task;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "AddTaskForm{" +
                "courseId='" + courseId + '\'' +
                ", course=" + course +
                ", task=" + task +
                '}';
    }
}
