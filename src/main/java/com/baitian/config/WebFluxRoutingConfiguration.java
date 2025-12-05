package com.baitian.config;

import com.baitian.user.handler.TaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author cn
 * @date 2025-12-05 09:44
 */
//@Configuration
public class WebFluxRoutingConfiguration {
    @Autowired
    private TaskHandler taskHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/user/list"), taskHandler::getAll)
                .andRoute(RequestPredicates.GET("/user/{id}"), taskHandler::getById);
    }

}
