package com.tamnt.spring.sample.service;


import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    Todo findById(Long id);

    void save(Todo todo);

    void delete(Long id);

    Page<Todo> findByConditionsPageableNative(TodoSearchForm searchForm, Pageable pageable);

    List<Todo> findByTaskId(Long taskId);
}
