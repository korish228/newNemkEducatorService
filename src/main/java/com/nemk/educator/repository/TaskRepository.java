package com.nemk.educator.repository;

import com.nemk.educator.model.Course;
import com.nemk.educator.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByCourse_Id(String courseId);
}
