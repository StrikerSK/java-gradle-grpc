package io.grpc.task.client.controller;

import io.grpc.task.client.service.ITaskServiceClient;
import io.grpc.task.commons.entity.TaskDAO;
import io.grpc.task.commons.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/task")
@RestController
@AllArgsConstructor
public class TaskController {

    private final ITaskServiceClient grpcService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createTask(@RequestBody TaskDAO taskEntity) {
        return grpcService.createTask(taskEntity);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDAO> readTask(@PathVariable String id) {
        try {
            TaskDAO task = grpcService.readTask(id);
            return ResponseEntity.ok(task);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
