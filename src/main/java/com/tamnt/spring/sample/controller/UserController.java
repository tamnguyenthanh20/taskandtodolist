package com.tamnt.spring.sample.controller;

import com.tamnt.spring.sample.model.Task;
import com.tamnt.spring.sample.model.User;
import com.tamnt.spring.sample.service.TaskService;
import com.tamnt.spring.sample.service.UserService;
import com.tamnt.spring.sample.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        userService.save(userForm);

        return "user/register-success";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassWord(Model model, @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        String actionMessage;

        if (userForm.getPasswordConfirm().equals(userForm.getPassword())) {
            User user = userService.findByUsername(userForm.getUsername());
            userService.changePassword(user, userForm.getPassword());
            actionMessage = "Your password has been successfully updated!";
        } else {
            actionMessage = "These passwords don't match!";
        }

        model.addAttribute("actionMessage", actionMessage);

        return "user/index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userIndex(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("userForm", user);
        model.addAttribute("tasks", userService.findAllTaskByUserName(principal.getName()));

        return "user/index";
    }


    @RequestMapping(value = "/user/saveTask", method = RequestMethod.POST)
    @ResponseBody
    public void saveTask(@RequestBody String taskName, @RequestBody String taskDesc, @RequestBody String action) {

        Task task = new Task();
        task.setName(taskName);
        task.setDescription(taskDesc);

        if ("DELETE".equals(action)) {
            taskService.delete(task);
        } else {
            taskService.save(task);
        }
    }

}
