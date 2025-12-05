package com.baitian.user.service;

import com.baitian.user.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cn
 * @date 2025-12-04 20:22
 */
@Service
public class UserService {
    private final Map<Integer, User> data = new ConcurrentHashMap<>();

    public UserService() {
        data.put(1, new User("zhangsan"));
        data.put(2, new User("lisi"));
        data.put(3, new User("wangwu"));
    }

    public Flux<User> list() {
        return Flux.fromIterable(data.values());
    }

    public Mono<User> getById(Integer id) {
        return Mono.justOrEmpty(data.get(id));
    }

    public Mono<Void> save(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
            int id = data.size() + 1;
            data.put(id, user);
        }).thenEmpty(Mono.empty());
    }
}
