package io.grpc.examples.controller;

import io.grpc.examples.chat.UserMessage;
import io.grpc.examples.service.ITodoService;
import io.grpc.examples.service.TodoGrpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class TodoController {

    private final ITodoService grpcService;

    public TodoController() {
        grpcService = new TodoGrpcService();
    }

    @GetMapping("/message")
    @ResponseBody
    public String sendMessage() {
        return grpcService.sendMessage();
    }

}
