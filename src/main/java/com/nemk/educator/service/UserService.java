package com.nemk.educator.service;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.User;
import com.nemk.educator.repository.CourseRepository;
import com.nemk.educator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;


    public void createUser(User user){
        user.setPassword(user.getPassword());
        this.userRepository.save(user);
    }

    public List<Course> getCoursesByUserName(String userName) {
        return this.courseRepository.findCoursesByUser_UserName(userName);
    }


    public User findOne(String userName){
        return this.userRepository.findByUserName(userName).get();
    }

    public boolean isUserPresent(String email) {
        User u = this.userRepository.findByEmail(email);
        if(u!=null){
            return true;
        }
        return false;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public List<User> findByEmailOrUserName(String name) {
        return this.userRepository.findAllByEmailLikeOrUserNameLike("%" + name + "%" ,"%" + name + "%" );
    }


}
