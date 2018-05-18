package com.tamnt.spring.sample.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by tamnt on 5/12/18.
 */

@Entity
@Table(name = "todo")
public class Todo {

    private Long id;
    private String name;
    private Date deadline;
    private boolean complete;
    private Task task;

    public Todo() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}