package com.tamnt.spring.sample.repository;

import com.tamnt.spring.sample.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.user.username = :userName")
    List<Task> findAllByUserName(@Param("userName") String userName);
}
