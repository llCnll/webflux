package com.baitian.user.controller;

import com.baitian.user.entity.User;
import com.baitian.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author cn
 * @date 2025-12-04 20:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public Flux<User> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/save")
    public Mono<Void> save(@RequestBody Mono<User> userMono) {
        return userService.save(userMono);
    }
}
