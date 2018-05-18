package com.tamnt.spring.sample.service.impl;


import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import com.tamnt.spring.sample.repository.TodoRepository;
import com.tamnt.spring.sample.repository.TodoRepositoryCustom;
import com.tamnt.spring.sample.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoRepositoryCustom todoRepositoryCustom;

    @Override
    public Todo findById(Long id) {
        return todoRepository.findOne(id);
    }

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.delete(id);
    }

    @Override
    public Page<Todo> findByConditionsPageableNative(TodoSearchForm searchForm, Pageable pageable) {
        return todoRepositoryCustom.findByConditionsPageable(searchForm, pageable);
    }

    @Override
    public List<Todo> findByTaskId(Long taskId) {
        return todoRepository.findByTaskId(taskId);
    }
}
