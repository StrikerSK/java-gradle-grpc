package io.grpc.task.client.entity;

import io.grpc.task.proto.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskEntity {

    private String id = "";
    private String name;
    private String description;
    private List<String> tags = new ArrayList<>();

    public Task toGrpcTask() {
        return Task.newBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .addAllTags(tags)
                .build();
    }

}
