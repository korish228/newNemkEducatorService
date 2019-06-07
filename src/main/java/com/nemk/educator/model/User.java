package com.nemk.educator.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @NotNull
    @Size(min = 2)
    @NotEmpty
    @Column(unique = true)
    private String userName;

    @Email
    @Size(min = 4)
    private String email;

    @NotNull
    @Size(min = 3)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Course> courses;

    private String role;


    public User() {
        this.id = UUID.randomUUID().toString();
        this.role = Role.USER.getName();
    }

    public User(String userName, String email, String password) {
        this();
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userName,  String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean addCourses(Course course) {
        return this.courses.add(course);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
