package com.docker.apiserver;

import java.util.Objects;

public class Todo {
    private String title;

    Todo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(title, todo.title);
    }

    @Override
    public String toString() {
        return "{" + "\"" + "title" + "\"" + ":" + "\"" + this.title + "\"" + "}";
    }

    @Override
    public int hashCode() {

        return Objects.hash(title);
    }
}
