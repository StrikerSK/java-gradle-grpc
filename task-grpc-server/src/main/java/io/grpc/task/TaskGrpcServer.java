package io.grpc.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskGrpcServer {

    public static void main(String[] args) {
        SpringApplication.run(TaskGrpcServer.class, args);
    }

}
