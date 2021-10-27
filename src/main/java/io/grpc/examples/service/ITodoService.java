package io.grpc.examples.service;

import io.grpc.examples.helloworld.HelloReply;

public interface ITodoService {

    HelloReply sendMessage();

}
