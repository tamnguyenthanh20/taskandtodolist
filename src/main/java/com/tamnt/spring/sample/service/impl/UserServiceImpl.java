package com.tamnt.spring.sample.service.impl;


import com.tamnt.spring.sample.constant.RoleEnum;
import com.tamnt.spring.sample.model.Role;
import com.tamnt.spring.sample.model.Task;
import com.tamnt.spring.sample.model.User;
import com.tamnt.spring.sample.repository.RoleRepository;
import com.tamnt.spring.sample.repository.TaskRepository;
import com.tamnt.spring.sample.repository.UserRepository;
import com.tamnt.spring.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleEnum.USER.name())); // default register user only
        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public List<Task> findAllTaskByUserName(String userName) {
        return taskRepository.findAllByUserName(userName);
    }
}
