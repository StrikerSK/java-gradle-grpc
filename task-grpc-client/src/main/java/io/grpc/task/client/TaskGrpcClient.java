package io.grpc.task.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskGrpcClient {

    public static void main(String[] args) {
        SpringApplication.run(TaskGrpcClient.class, args);
    }

}
