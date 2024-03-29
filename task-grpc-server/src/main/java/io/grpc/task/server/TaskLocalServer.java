package io.grpc.task.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.task.service.TaskConsoleService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TaskLocalServer {
  private Server server;

  private void start() throws IOException {
    /* The port on which the server should run */
    int port = 9000;
    server = ServerBuilder.forPort(port)
        .addService(new TaskGrpcService(new TaskConsoleService()))
        .build()
        .start();

    log.info("Server started, listening on " + port);

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        try {
          TaskLocalServer.this.stop();
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final TaskLocalServer server = new TaskLocalServer();
    server.start();
    server.blockUntilShutdown();
  }

}
