package io.grpc.examples.service;

import io.grpc.examples.chat.UserMessage;
import io.grpc.examples.client.ChatServiceClient;
import org.springframework.stereotype.Service;

@Service
public class TodoGrpcService implements ITodoService {

    private final ChatServiceClient client;

    public TodoGrpcService() {
        client = new ChatServiceClient();
    }

    @Override
    public UserMessage sendMessage() {
        return client.sendRequest("Tester");
    }

}
