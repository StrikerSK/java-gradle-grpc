package io.grpc.todo.controller;

import io.grpc.todo.client.TodoServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public class TodoController {

    private final TodoServiceClient grpcService;

    @GetMapping("/todo")
    @ResponseBody
    public String createTodo() {
        return grpcService.CreateTodo("First todo", "Description of first todo");
    }

}
