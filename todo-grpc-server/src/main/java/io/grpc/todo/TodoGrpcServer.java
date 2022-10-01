package io.grpc.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoGrpcServer {

    public static void main(String[] args) {
        SpringApplication.run(TodoGrpcServer.class, args);
    }

}
