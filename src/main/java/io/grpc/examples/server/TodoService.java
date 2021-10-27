package io.grpc.examples.server;

import com.google.protobuf.Empty;
import io.grpc.examples.chat.ChatServiceGrpc;
import io.grpc.examples.chat.UserMessage;
import io.grpc.examples.todo.IdRequest;
import io.grpc.examples.todo.NewTodo;
import io.grpc.examples.todo.PersistedTodo;
import io.grpc.examples.todo.TodoServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class TodoService extends TodoServiceGrpc.TodoServiceImplBase {

    private List<PersistedTodo> todos = new ArrayList<>();

    @Override
    public void createTodo(NewTodo request, StreamObserver<IdRequest> responseObserver) {
        PersistedTodo newTodo = PersistedTodo.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setDone(false)
                .build();

        todos.add(newTodo);
        responseObserver.onNext(IdRequest.newBuilder().setId(newTodo.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTodo(IdRequest request, StreamObserver<PersistedTodo> responseObserver) {
        super.getTodo(request, responseObserver);
    }

    @Override
    public void getTodos(Empty request, StreamObserver<PersistedTodo> responseObserver) {
        super.getTodos(request, responseObserver);
    }
}
