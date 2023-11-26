package com.example.springboot.learnspringboot.todo;

import java.time.LocalDate;
import java.util.List;

public class TodoServiece {
    private static List<Todo> todos;
    static {
        todos.add(new Todo(1,"lee","learn AWS",
                LocalDate.now().plusYears(1),false));
        todos.add(new Todo(1,"lee","learn DevOps",
                LocalDate.now().plusYears(2),false));
        todos.add(new Todo(1,"lee","learn Full Stack",
                LocalDate.now().plusYears(3),false));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }

}
