package io.grpc.task.service;

import io.grpc.task.proto.Task;

public interface ITaskService {

    String createTask(Task task);
    Task readTask(String id);

}
