package io.grpc.task.server;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.grpc.task.proto.Task;
import io.grpc.task.proto.TaskRequest;
import io.grpc.task.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import static io.grpc.task.proto.TaskServiceGrpc.*;

@Slf4j
@GrpcService
public class TaskGrpcService extends TaskServiceImplBase {

    private final ITaskService todoService;

    public TaskGrpcService(ITaskService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void createTask(Task task, StreamObserver<TaskRequest> responseObserver) {
        try {
            String id = todoService.createTask(task);
            responseObserver.onNext(TaskRequest.newBuilder().setId(id).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error while creating todo", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error while adding todo")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void readTask(TaskRequest request, StreamObserver<Task> responseObserver) {
        try {
            Task task = todoService.readTask(request.getId());
            responseObserver.onNext(task);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error while reading todo", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error while reading todo")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}
