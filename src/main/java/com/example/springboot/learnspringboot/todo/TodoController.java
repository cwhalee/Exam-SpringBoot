package com.example.springboot.learnspringboot.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String newTodoPage(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username,"",LocalDate.now().plusYears(1), false);
        model.addAttribute("todoList", todo);
        return "todo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid @ModelAttribute("todo")Todo todo, BindingResult result) {
        if (result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todoServiece.addTodo(username, todo.getDescription(),
                LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }
}
