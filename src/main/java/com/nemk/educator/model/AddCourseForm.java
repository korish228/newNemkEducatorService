package com.nemk.educator.model;


public class AddCourseForm {

    private String username;

    private User user;
    private Course course;

    public AddCourseForm(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "AddCourseForm{" +
                "username='" + username + '\'' +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
