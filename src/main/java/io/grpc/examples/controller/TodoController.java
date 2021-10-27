package io.grpc.examples.controller;

import io.grpc.examples.chat.UserMessage;
import io.grpc.examples.service.ITodoService;
import io.grpc.examples.service.TodoGrpcService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TodoController {

    private final ITodoService grpcService;

    public TodoController() {
        grpcService = new TodoGrpcService();
    }

    @GetMapping("/message")
    public UserMessage sendMessage() {
        return grpcService.sendMessage();
    }

}
