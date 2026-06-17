package com.example.notification.service;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.store.NotificationStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationEventBus eventBus;
    private final NotificationStore store;

    public Mono<Void> sendNotification(
            NotificationRequest request) {

        return Mono.fromRunnable(() -> {

            String message =
                    "EMAIL TO: "
                            + request.getEmail()
                            + " | MESSAGE: "
                            + request.getMessage();

            store.add(message);
            eventBus.publish(message);
        });
    }
}
