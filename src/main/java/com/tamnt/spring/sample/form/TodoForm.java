package com.tamnt.spring.sample.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by tamnt on 5/13/18.
 */
public class TodoForm {

    private Long id;
    private String todoName;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate todoDeadline;
    private Long taskId;

    public TodoForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public LocalDate getTodoDeadline() {
        return todoDeadline;
    }

    public void setTodoDeadline(LocalDate todoDeadline) {
        this.todoDeadline = todoDeadline;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
