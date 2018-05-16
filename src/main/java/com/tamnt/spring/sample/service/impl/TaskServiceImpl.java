package com.tamnt.spring.sample.service.impl;


import com.tamnt.spring.sample.model.Task;
import com.tamnt.spring.sample.model.Todo;
import com.tamnt.spring.sample.repository.TaskRepository;
import com.tamnt.spring.sample.repository.TodoRepository;
import com.tamnt.spring.sample.service.TaskService;
import com.tamnt.spring.sample.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
