package com.nemk.educator.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @Column(name = "course_id")
    private String id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    private String requirements;

    @Size(min=5, max=200)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Task> tasks;

    public Course() {
        this.tasks = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public Course(String title,String requirements, String description,User user) {
        this();
        this.title = title;
        this.requirements = requirements;
        this.description = description;
        this.user = user;
    }

    public Course(String id, String title, String requirements, String description,User user) {
        this(title,requirements,description,user);
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
