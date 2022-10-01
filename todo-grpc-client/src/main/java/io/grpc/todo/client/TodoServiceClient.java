package io.grpc.todo.client;

import io.grpc.StatusRuntimeException;
import io.grpc.todo.Todo;
import io.grpc.todo.TodoRequest;
import io.grpc.todo.TodoServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TodoServiceClient {

    @GrpcClient("todos")
    private TodoServiceGrpc.TodoServiceBlockingStub todoStub;

    public String CreateTodo(String name, String description) {
        try {
            Todo requestMessage = Todo.newBuilder()
                    .setName(name)
                    .setDescription(description)
                    .setDone(false)
                    .build();
            TodoRequest response = todoStub.createTodo(requestMessage);
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
