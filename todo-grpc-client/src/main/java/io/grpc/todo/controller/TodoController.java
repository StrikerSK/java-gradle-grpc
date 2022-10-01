package io.grpc.todo.controller;

import io.grpc.todo.entity.TaskEntity;
import io.grpc.todo.client.TodoServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public class TodoController {

    private final TodoServiceClient grpcService;

    @PostMapping(path = "/todo", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createTodo(@RequestBody TaskEntity taskEntity) {
        return grpcService.CreateTodo(taskEntity.toGrpcTask());
    }

}
