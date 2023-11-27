package com.example.springboot.learnspringboot.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiece {
    private static List<Todo> todos = new ArrayList<>();
    static {
        todos.add(new Todo(1,"lee","learn AWS",
                LocalDate.now().plusYears(1),false));
        todos.add(new Todo(2,"lee","learn DevOps",
                LocalDate.now().plusYears(2),false));
        todos.add(new Todo(3,"lee","learn Full Stack",
                LocalDate.now().plusYears(3),false));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }

}
