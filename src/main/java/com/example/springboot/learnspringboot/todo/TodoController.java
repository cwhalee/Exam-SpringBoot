package com.example.springboot.learnspringboot.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String username = (String)model.get("name");
        List<Todo> todos = todoServiece.findByUsername(username);
        model.addAttribute("todos",todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String newTodoPage(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username,"",LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todoServiece.addTodo(username, todo.getDescription(),
                todo.getTargetDate(), false);
        return "redirect:list-todos";
    }
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoServiece.deleteById(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoServiece.findById(id);
        model.addAttribute("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodoPro(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todoServiece.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
