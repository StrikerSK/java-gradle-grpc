package io.grpc.todo.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.todo.TodoServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TodoServiceClient {

//  private final TodoServiceGrpc.TodoServiceBlockingStub stub;
//  private final ManagedChannel channel;
//
//  /** Construct client for accessing HelloWorld server using the existing channel. */
//  public TodoServiceClient() {
//      String target = "localhost:9000";
//      channel = ManagedChannelBuilder.forTarget(target)
//              .usePlaintext()
//              .build();
//
//      stub = TodoServiceGrpc.newBlockingStub(channel);
//  }
//
//  public String CreateTodo(String name, String description) {
//    try {
//      NewTodo requestMessage = NewTodo.newBuilder()
//              .setName(name)
//              .setDescription(description)
//              .setDone(false)
//              .build();
//      IdRequest responseMessage = stub.createTodo(requestMessage);
//      return responseMessage.getId();
//    } catch (StatusRuntimeException e) {
//      log.warn("RPC failed: {}", e.getStatus());
//      throw new RuntimeException("Something went wrong");
//    }
//  }
}
