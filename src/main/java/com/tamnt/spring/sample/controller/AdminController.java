package com.tamnt.spring.sample.controller;

import com.tamnt.spring.sample.constant.Pager;
import com.tamnt.spring.sample.form.TodoSearchForm;
import com.tamnt.spring.sample.model.Todo;
import com.tamnt.spring.sample.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AdminController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminIndex(@RequestParam("page") Optional<Integer> page,
                             @ModelAttribute("searchForm") TodoSearchForm searchForm, Model model) {

        int pageNumber = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Todo> todos = todoService.findByConditionsPageable(searchForm, new PageRequest(pageNumber, INITIAL_PAGE_SIZE)); // show 5 items per page

        Pager pager = new Pager(todos.getTotalPages(), todos.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("todos", todos);
        model.addAttribute("pager", pager);

        return "admin/index";
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.POST)
    public String adminTodoFilter(TodoSearchForm searchForm,
            BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        redirectAttrs.addFlashAttribute("searchForm", searchForm);

        return "redirect:/admin";
    }

}