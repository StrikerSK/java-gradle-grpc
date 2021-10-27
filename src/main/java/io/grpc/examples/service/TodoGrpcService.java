package io.grpc.examples.service;

import io.grpc.examples.client.HelloWorldClient;
import io.grpc.examples.helloworld.HelloReply;
import org.springframework.stereotype.Service;

@Service
public class TodoGrpcService implements ITodoService {

    private final HelloWorldClient client;

    public TodoGrpcService() {
        client = new HelloWorldClient();
    }

    @Override
    public HelloReply sendMessage() {
        return client.sendRequest("Tester");
    }

}
