package io.grpc.task.client.service;

import io.grpc.task.commons.entity.TaskDAO;
import io.grpc.task.proto.Task;

public interface ITaskServiceClient {

    String createTask(TaskDAO task);
    TaskDAO readTask(String id);

}
