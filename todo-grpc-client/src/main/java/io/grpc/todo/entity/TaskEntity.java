package io.grpc.todo.entity;

import io.grpc.todo.Todo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskEntity {

    private String id = "";
    private String name;
    private String description;
    private List<String> tags = new ArrayList<>();

    public Todo toGrpcTask() {
        return Todo.newBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .addAllTags(tags)
                .build();
    }

}
