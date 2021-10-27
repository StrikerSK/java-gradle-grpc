package io.grpc.examples.controller;

import io.grpc.examples.service.ITodoService;
import io.grpc.examples.service.TodoGrpcService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class TodoController {

    private final ITodoService grpcService;

    public TodoController() {
        grpcService = new TodoGrpcService();
    }

    @GetMapping("/message/{username}")
    @ResponseBody
    public String sendMessage(@PathVariable String username) {
        return grpcService.sendMessage(username);
    }

}
