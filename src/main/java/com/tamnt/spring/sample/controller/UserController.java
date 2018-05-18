package com.tamnt.spring.sample.controller;

import com.tamnt.spring.sample.form.TodoForm;
import com.tamnt.spring.sample.model.Task;
import com.tamnt.spring.sample.model.Todo;
import com.tamnt.spring.sample.model.User;
import com.tamnt.spring.sample.service.TaskService;
import com.tamnt.spring.sample.service.TodoService;
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
    private TodoService todoService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userIndex(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("userForm", user);
        model.addAttribute("tasks", userService.findAllTaskByUserName(principal.getName()));

        return "user/index";
    }

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

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String changePasswordGet(Model model, Principal principal) {

        User loggedUser =  userService.findByUsername(principal.getName());
        model.addAttribute("userForm", loggedUser);

        return "user/changePassword";
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
    public String changePassWord(Model model, @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Principal principal) {

        String actionMessage;

        if (userForm.getPasswordConfirm().equals(userForm.getPassword())) {
            User user = userService.findByUsername(principal.getName());
            userService.changePassword(user, userForm.getPassword());
            actionMessage = "Your password has been successfully updated!";
        } else {
            actionMessage = "These passwords don't match!";
        }

        model.addAttribute("actionMessage", actionMessage);

        return "redirect:/";
    }

    //===============================================================================================================

    @RequestMapping(value = "/user/tasks", method = RequestMethod.GET)
    public String listTasksOfUser(Model model, Principal principal) {
        model.addAttribute("tasks", userService.findAllTaskByUserName(principal.getName()));

        return "user/tasks";
    }

    @RequestMapping(value = "/user/task/create", method = RequestMethod.GET)
    public String createTask(Model model) {

        model.addAttribute("task", new Task());
        return "user/task";
    }

    @RequestMapping(value = "/user/task/save", method = RequestMethod.POST)
    public String createTaskAction(Task task , BindingResult bindingResult, Principal principal) {

        User loggedUser =  userService.findByUsername(principal.getName());
        task.setUser(loggedUser);

        taskService.save(task);

        return "redirect:/user/tasks";
    }

    @RequestMapping(value = "/user/task/update/{taskId}", method = RequestMethod.GET)
    public String updateTask(@PathVariable Long taskId, Model model) {

        model.addAttribute("task", taskService.findById(taskId));

        return "/user/task";
    }

    @RequestMapping(value = "/user/task/delete/{taskId}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Long taskId) {

        taskService.delete(taskId);

        return "redirect:/user/tasks";
    }

    @RequestMapping(value = "/user/task", method = RequestMethod.GET)
    public String listTodoByTaskid(Model model, @RequestParam(name = "taskid") Long taskid, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userForm", user);
        model.addAttribute("currentTaskId", taskid);
        model.addAttribute("todos", todoService.findByTaskId(taskid));

        return "user/index";
    }

    //===============================================================================================================

    @RequestMapping(value = "/user/todo/create", method = RequestMethod.GET)
    public String createTodo(@RequestParam(name = "taskId") Long taskId, Model model) {

        TodoForm todoForm = new TodoForm();
        todoForm.setTaskId(taskId);

        model.addAttribute("todoForm", todoForm);
        return "user/todo";
    }

    @RequestMapping(value = "/user/todo/save", method = RequestMethod.POST)
    public String saveTodoAction(TodoForm todoForm , BindingResult bindingResult, Principal principal) {

        Todo todo;

        if (todoForm.getId() == null) {
            todo = new Todo();
            todo.setComplete(false);
        } else {
            todo = todoService.findById(todoForm.getId());
        }

        todo.setName(todoForm.getTodoName());
        todo.setDeadline(todoForm.getTodoDeadline());

        todo.setTask(taskService.findById(todoForm.getTaskId()));

        todoService.save(todo);

        return "redirect:/user/task?taskid=" + todoForm.getTaskId();
    }

    @RequestMapping(value = "/user/todo/update/{todoId}", method = RequestMethod.GET)
    public String updateTodo(@PathVariable Long todoId, Model model) {

        Todo todo = todoService.findById(todoId);

        TodoForm todoForm = new TodoForm();
        todoForm.setId(todo.getId());
        todoForm.setTodoName(todo.getName());
        todoForm.setTodoDeadline(todo.getDeadline());
        todoForm.setTaskId(todo.getTask().getId());

        model.addAttribute("todoForm", todoForm);

        return "/user/todo";
    }

    @RequestMapping(value = "/user/todo/delete/{todoId}", method = RequestMethod.GET)
    public String deleteTodo(@PathVariable Long todoId) {

        Long taskId = todoService.findById(todoId).getTask().getId();
        todoService.delete(todoId);

        return "redirect:/user/task?taskid=" + taskId;
    }


    @RequestMapping(value = "/user/todo/markdone/{todoId}", method = RequestMethod.GET)
    public String markTodoDone(@PathVariable Long todoId) {

        Todo todo = todoService.findById(todoId);
        todo.setComplete(true);
        todoService.save(todo);
        Long taskId = todo.getTask().getId();

        return "redirect:/user/task?taskid=" + taskId;
    }
}
