package com.tamnt.spring.sample.repository;

import com.tamnt.spring.sample.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    @Query("select t from Todo t where t.task.id = :taskId")
    List<Todo> findByTaskId(@Param("taskId") Long taskId);

}
