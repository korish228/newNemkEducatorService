package com.nemk.educator.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Course {

    @Id
    @Column(name = "course_id")
    private String id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Course() {
        this.id = UUID.randomUUID().toString();
    }

    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", tasks=" + tasks +
                '}';
    }
}
