package io.grpc.examples.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.server.HelloWorldServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
@Slf4j
@Component
public class HelloWorldClient {

  private final GreeterGrpc.GreeterBlockingStub blockingStub;
  private final ManagedChannel channel;

  /** Construct client for accessing HelloWorld server using the existing channel. */
  public HelloWorldClient() {
      String target = "localhost:9000";
      channel = ManagedChannelBuilder.forTarget(target)
              .usePlaintext()
              .build();

      blockingStub = GreeterGrpc.newBlockingStub(channel);
  }

  public HelloReply sendRequest(String name) {
    try {
      log.info("Will try to greet user: {}", name);
      HelloRequest request = HelloRequest.newBuilder().setName(name).build();
      return blockingStub.sayHello(request);
    } catch (StatusRuntimeException e) {
      log.warn("RPC failed: {}", e.getStatus());
      throw new RuntimeException("Something went wrong");
    }
  }
}
