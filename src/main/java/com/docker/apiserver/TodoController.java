package com.docker.apiserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("work"));
        todos.add(new Todo("personal"));
        todos.add(new Todo("family"));
        return todos;
    }

}
