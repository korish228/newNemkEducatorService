package com.nemk.educator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    private String id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name = "course_id")
    private Course course;

    private String urlToVideoFile;

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

    public Task(String title,String urlToVideoFile, Course course) {
        this();
        this.title = title;
        this.course = course;
        this.urlToVideoFile = urlToVideoFile;
    }

    public Task(String id, String title,String urlToVideoFile, Course course) {
        this(title,urlToVideoFile, course);
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getUrlToVideoFile() {
        return urlToVideoFile;
    }

    public void setUrlToVideoFile(String urlToVideoFile) {
        this.urlToVideoFile = urlToVideoFile;
    }
}
