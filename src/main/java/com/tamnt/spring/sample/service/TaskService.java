package com.tamnt.spring.sample.service;

import com.tamnt.spring.sample.model.Task;

public interface TaskService {
    Task save(Task task);

    void delete(Task task);
}
