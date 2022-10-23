package io.grpc.task.client.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.task.commons.entity.TaskDAO;
import io.grpc.task.commons.exception.NotFoundException;
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

    public String createTask(TaskDAO task) {
        try {
            TaskRequest response = stub.createTask(task.toGrpcTask());
            return response.getId();
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
            throw new RuntimeException("Something went wrong");
        } catch (Exception e) {
            log.warn("Something went wrong: {}", e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public TaskDAO readTask(String id) {
        try {
            TaskRequest request = TaskRequest.newBuilder().setId(id).build();
            return new TaskDAO(stub.readTask(request.toBuilder().build()));
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
            if (Status.NOT_FOUND.equals(e.getStatus())) {
                throw new NotFoundException(String.format("Task [%s] not found", id));
            } else {
                throw new RuntimeException("Something went wrong");
            }
        } catch (Exception e) {
            log.warn("Something went wrong: {}", e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
    }

}
