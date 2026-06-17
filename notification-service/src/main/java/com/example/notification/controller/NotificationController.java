package com.example.notification.controller;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.service.NotificationService;
import com.example.notification.store.NotificationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;
    private final NotificationStore store;

    @PostMapping
    public Mono<Void> send(
            @RequestBody NotificationRequest request) {

        return service.sendNotification(request);
    }

    @GetMapping
    public List<String> getNotifications() {
        return store.getAll();
    }
}
