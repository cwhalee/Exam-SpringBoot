package com.example.springboot.learnspringboot.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    public TodoController(TodoServiece todoServiece) {
        this.todoServiece = todoServiece;
    }

    private TodoServiece todoServiece;

    @RequestMapping("list-todos")
    public String ListAllTodos(ModelMap model) {
        List<Todo> todos = todoServiece.findByUsername("lee");
        model.addAttribute("todos",todos);
        return "listTodos";
    }
}
