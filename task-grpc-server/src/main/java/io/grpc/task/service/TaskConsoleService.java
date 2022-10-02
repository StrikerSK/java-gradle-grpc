package io.grpc.task.service;

import io.grpc.task.proto.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskConsoleService implements ITaskService {

    @Override
    public String createTask(Task Task) {
        System.out.println("Task received:");
        System.out.println("Title: " + Task.getName());
        System.out.println("Description: " + Task.getDescription());
        return "Task created!";
    }

    @Override
    public Task readTask(String id) {
        return Task.newBuilder()
                .setId(id)
                .setName("Dummy name")
                .setDescription("Dummy description")
                .setDone(false)
                .addAllTags(List.of("tag1", "tag2"))
                .build();
    }

}
