package io.grpc.task.server;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.grpc.task.commons.exception.NotFoundException;
import io.grpc.task.proto.Task;
import io.grpc.task.proto.TaskRequest;
import io.grpc.task.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import static io.grpc.task.proto.TaskServiceGrpc.*;

@Slf4j
@GrpcService
public class TaskGrpcService extends TaskServiceImplBase {

    private final ITaskService taskService;

    public TaskGrpcService(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void createTask(Task task, StreamObserver<TaskRequest> responseObserver) {
        try {
            String id = taskService.createTask(task);
            responseObserver.onNext(TaskRequest.newBuilder().setId(id).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error while creating task", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error while adding task")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void readTask(TaskRequest request, StreamObserver<Task> responseObserver) {
        try {
            Task task = taskService.readTask(request.getId());
            responseObserver.onNext(task);
            responseObserver.onCompleted();
        } catch (NotFoundException e){
            log.error("Task {} cannot be found", request.getId(), e);
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription(String.format("Task [%s] not found", request.getId()))
                    .asRuntimeException());
        } catch (Exception e) {
            log.error("Error while reading task", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error while reading task")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}
