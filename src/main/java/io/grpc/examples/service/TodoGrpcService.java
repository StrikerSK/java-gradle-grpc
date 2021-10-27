package io.grpc.examples.service;

import io.grpc.examples.client.ChatServiceClient;
import org.springframework.stereotype.Service;

@Service
public class TodoGrpcService implements ITodoService {

    private final ChatServiceClient client;

    public TodoGrpcService() {
        client = new ChatServiceClient();
    }

    @Override
    public String sendMessage(String name) {
        return client.sendRequest(name);
    }

}
