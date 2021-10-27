package io.grpc.examples.server;

import io.grpc.examples.chat.ChatServiceGrpc;
import io.grpc.examples.chat.UserMessage;
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends ChatServiceGrpc.ChatServiceImplBase {

    @Override
    public void sayHello(UserMessage request, StreamObserver<UserMessage> responseObserver) {
        UserMessage reply = UserMessage.newBuilder().setBody("Hello " + request.getBody()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
