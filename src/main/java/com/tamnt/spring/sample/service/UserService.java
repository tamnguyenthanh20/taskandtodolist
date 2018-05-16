package com.tamnt.spring.sample.service;


import com.tamnt.spring.sample.model.Task;
import com.tamnt.spring.sample.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    void changePassword(User user, String newPassword);

    List<Task> findAllTaskByUserName(String userName);
}
