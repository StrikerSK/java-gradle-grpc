package io.grpc.examples.service;

import io.grpc.examples.chat.UserMessage;

public interface ITodoService {

    UserMessage sendMessage();

}
