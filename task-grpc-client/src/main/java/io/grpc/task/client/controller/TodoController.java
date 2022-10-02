package io.grpc.task.client.controller;

import io.grpc.task.client.entity.TaskEntity;
import io.grpc.task.client.service.TaskServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public class TodoController {

    private final TaskServiceClient grpcService;

    @PostMapping(path = "/task", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createTodo(@RequestBody TaskEntity taskEntity) {
        return grpcService.CreateTask(taskEntity.toGrpcTask());
    }

}
