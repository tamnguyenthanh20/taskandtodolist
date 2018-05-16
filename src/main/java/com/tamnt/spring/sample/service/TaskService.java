package com.tamnt.spring.sample.service;

import com.tamnt.spring.sample.model.Task;

public interface TaskService {

    Task findById(Long taskId);

    Task save(Task task);

    void delete(Long taskId);
}
