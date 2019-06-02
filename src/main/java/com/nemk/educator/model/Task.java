package com.nemk.educator.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @Column(name = "task_id")
    private String id;
    private String title;
    private String url;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Task() {
        this.id = UUID.randomUUID().toString();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
