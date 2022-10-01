package io.grpc.todo.server;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.grpc.todo.Todo;
import io.grpc.todo.TodoRequest;
import io.grpc.todo.TodoServiceGrpc.TodoServiceImplBase;
import io.grpc.todo.service.ITodoService;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class TodoGrpcService extends TodoServiceImplBase {

    private final ITodoService todoService;

    public TodoGrpcService(ITodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void createTodo(Todo todo, StreamObserver<TodoRequest> responseObserver) {
        try {
            String id = todoService.createTodo(todo);
            responseObserver.onNext(TodoRequest.newBuilder().setId(id).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error while creating todo", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error while adding todo")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

}
