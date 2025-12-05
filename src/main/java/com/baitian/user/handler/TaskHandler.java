package com.baitian.user.handler;

import com.baitian.user.entity.User;
import com.baitian.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author cn
 * @date 2025-12-04 20:37
 */
@Component
public class TaskHandler {
    @Autowired
    private UserService userService;

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        // 获取ID值
        String id = serverRequest.pathVariable("id");
        // 空值处理
        Mono<ServerResponse> response = ServerResponse.notFound().build();

        Mono<User> userMono = userService.getById(Integer.valueOf(id));

        return userMono.flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(user), User.class));
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        Flux<User> users = userService.list();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }


    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.save(userMono));
    }
}
