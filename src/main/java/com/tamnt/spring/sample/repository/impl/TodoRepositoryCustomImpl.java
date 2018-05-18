package com.tamnt.spring.sample.repository.impl;

import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import com.tamnt.spring.sample.repository.TodoRepository;
import com.tamnt.spring.sample.repository.TodoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Page<Todo> findByConditionsPageable(TodoSearchForm todoSearchForm, Pageable pageable) {

        boolean hasWhere = false;
        StringBuilder queryStr = new StringBuilder("SELECT * FROM todo t ");

        if (!StringUtils.isEmpty(todoSearchForm.getTaskName())) {
            queryStr.append("inner join task tk on t.task_id = tk.id  ")
                    .append("WHERE 1=1 ")
                    .append("AND tk.name LIKE ")
                    .append("'%").append(todoSearchForm.getTaskName())
                    .append("%'");

            hasWhere = true;
        }

        if (!StringUtils.isEmpty(todoSearchForm.getTodoName())) {

            if (!hasWhere) {
                queryStr.append(" WHERE 1=1 ");
                hasWhere = true;
            }

            queryStr.append("AND t.name LIKE ").append("'%").append(todoSearchForm.getTodoName()).append("%'");
        }

        if (!StringUtils.isEmpty(todoSearchForm.getTodoStatus())) {

            if (!hasWhere) {
                queryStr.append(" WHERE 1=1 ");
                hasWhere = true;
            }

            boolean isComplete = false;
            if ("1".equals(todoSearchForm.getTodoStatus())) {
                isComplete = true;
            }

            queryStr.append("AND t.complete = ").append(isComplete);
        }

        if (!StringUtils.isEmpty(todoSearchForm.getTodoDeadline())) {
            if (!hasWhere) {
                queryStr.append(" WHERE 1=1 ");
            }

            queryStr.append("AND DATE(t.deadline) = ").append("'").append(todoSearchForm.getTodoDeadline()).append("'");
        }

        Query query =  em.createNativeQuery(queryStr.toString(), Todo.class);
        query.setFirstResult(pageable.getOffset() );
        query.setMaxResults((pageable.getPageNumber() + 1) * pageable.getPageSize());

        List<Todo> todos = query.getResultList();

        return listTodoByPage(pageable, todos);
    }

    private Page<Todo> listTodoByPage(Pageable pageable, List<Todo> objectlist) {

        return new PageImpl<>(objectlist, pageable, todoRepository.count());
    }

}
