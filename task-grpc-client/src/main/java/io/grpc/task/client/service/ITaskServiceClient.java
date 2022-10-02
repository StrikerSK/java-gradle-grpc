package io.grpc.task.client.service;

import io.grpc.task.proto.Task;

public interface ITaskServiceClient {

    String createTask(Task task);

}
