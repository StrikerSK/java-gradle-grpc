package io.grpc.todo.service;

import io.grpc.todo.Todo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoConsoleService implements ITodoService {

    @Override
    public String createTodo(Todo todo) {
        System.out.println("Todo created:");
        System.out.println("Title: " + todo.getName());
        System.out.println("Description: " + todo.getDescription());
        return "Todo created!";
    }

}
