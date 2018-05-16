package com.tamnt.spring.sample.repository;

import com.tamnt.spring.sample.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    @Query("select t from Todo t where t.name = :todoName")
    Page<Todo> findByConditionTodoNamePageable(@Param("todoName") String todoName, Pageable pageable);

    @Query("select t from Todo t where t.complete = :todoStatus")
    Page<Todo> findByConditionTodoStatusPageable(@Param("todoStatus") String todoStatus, Pageable pageable);

    @Query("select t from Todo t where t.task.name = :taskName")
    Page<Todo> findByConditionTaskNamePageable(@Param("taskName") String taskName, Pageable pageable);

    @Query("select t from Todo t where t.deadline = :todoDeadline")
    Page<Todo> findByConditionTodoDeadlinePageable(@Param("todoDeadline") LocalDate todoDeadline, Pageable pageable);

    @Query("select t from Todo t where t.task.id = :taskId")
    List<Todo> findByTaskId(@Param("taskId") Long taskId);

}
