package com.ritesh.microservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageRestController {

    @Value("${config-refresh-message}")
    private String message;

    @Value("${environment-value}")
    private String envValue;

    @GetMapping("/config-refresh-message")
    String getConfigRefreshMessage() {
        return message;
    }

    @GetMapping("/environment-value")
    String getEnvironmentValue() {
        return envValue;
    }

}
