package io.grpc.task.commons.entity;

import io.grpc.task.proto.Task;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TaskDAO implements Serializable {

    private String id;
    private String name;
    private String description;
    private boolean done;
    private List<String> tags;

    public TaskDAO() {
    }

    public TaskDAO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.done = task.getDone();
        this.tags = task.getTagsList();
    }

    public Task toGrpcTask() {
        return Task.newBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setDone(done)
                .addAllTags(tags)
                .build();
    }

}
