package com.nemk.educator.repository;

import com.nemk.educator.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findAllByUserId(String id);

    List<Course> findAllByTitleLike(String title);

    List<Course> findCoursesByUser_UserName(String userName);

}
