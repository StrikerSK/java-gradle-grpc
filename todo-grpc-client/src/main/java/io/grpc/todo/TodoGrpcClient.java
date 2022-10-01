package io.grpc.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoGrpcClient {

    public static void main(String[] args) {
        SpringApplication.run(TodoGrpcClient.class, args);
    }

}
