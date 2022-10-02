package io.grpc.task.client.service;

import io.grpc.StatusRuntimeException;
import io.grpc.task.proto.Task;
import io.grpc.task.proto.TaskRequest;
import io.grpc.task.proto.TaskServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskServiceClient implements ITaskServiceClient {

    @GrpcClient("tasks")
    private TaskServiceGrpc.TaskServiceBlockingStub stub;

    public String createTask(Task task) {
        try {
            TaskRequest response = stub.createTask(task);
            return response.getId();
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
            throw new RuntimeException("Something went wrong");
        } catch (Exception e) {
            log.warn("Something went wrong: {}", e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
    }

}
