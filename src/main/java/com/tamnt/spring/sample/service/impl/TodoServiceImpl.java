package com.tamnt.spring.sample.service.impl;


import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import com.tamnt.spring.sample.repository.TodoRepository;
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
    public Page<Todo> findAllPageable(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Page<Todo> findByConditionsPageable(TodoSearchForm searchForm, Pageable pageable) {

        // Current support search by one field only
        // If all of them are empty, search all

        if (StringUtils.isEmpty(searchForm.getTodoName()) && StringUtils.isEmpty(searchForm.getTodoDeadline())
                && StringUtils.isEmpty(searchForm.getTodoStatus()) && StringUtils.isEmpty(searchForm.getTaskName())){
            return findAllPageable(pageable);
        } else if (!StringUtils.isEmpty(searchForm.getTodoName())) {
            return todoRepository.findByConditionTodoNamePageable(searchForm.getTodoName(), pageable);
        } else if (!StringUtils.isEmpty(searchForm.getTodoStatus())) {

            boolean isComplete = false;
            if ("1".equals(searchForm.getTodoStatus())) {
                isComplete = true;
            }

            return todoRepository.findByConditionTodoStatusPageable(isComplete, pageable);

        } else if (!StringUtils.isEmpty(searchForm.getTodoDeadline())) {
            return todoRepository.findByConditionTodoDeadlinePageable(searchForm.getTodoDeadline(), pageable);
        } else if (!StringUtils.isEmpty(searchForm.getTaskName())) {
            return todoRepository.findByConditionTaskNamePageable(searchForm.getTaskName(), pageable);
        }

        return null;
    }

    @Override
    public List<Todo> findByTaskId(Long taskId) {
        return todoRepository.findByTaskId(taskId);
    }
}
