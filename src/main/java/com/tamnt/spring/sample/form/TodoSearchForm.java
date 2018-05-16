package com.tamnt.spring.sample.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by tamnt on 5/13/18.
 */
public class TodoSearchForm {

    private String todoStatus;
    private String todoName;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate todoDeadline;
    private String taskName;

    public TodoSearchForm() {
    }

    public String getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(String todoStatus) {
        this.todoStatus = todoStatus;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
