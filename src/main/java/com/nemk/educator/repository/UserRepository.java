package com.nemk.educator.repository;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByEmailLikeOrUserNameLike(String email,String userName);

    List<Course> findAllByUserName(String userName);

    List<User> findAllByRole(String role);

    Optional<User> findByUserName(String name);

    User findByEmail(String email);

//    List<Course> findAllCoursesByUserName(String userName);
}
