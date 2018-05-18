package com.tamnt.spring.sample.repository;

import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepositoryCustom {

    Page<Todo> findByConditionsPageable(TodoSearchForm todoSearchForm, Pageable pageable);

}
