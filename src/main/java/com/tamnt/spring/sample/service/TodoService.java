package com.tamnt.spring.sample.service;


import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    Page<Todo> findAllPageable(Pageable pageable);

    Page<Todo> findByConditionsPageable(TodoSearchForm searchForm, Pageable pageable);

    List<Todo> findByTaskId(Long taskId);
}
