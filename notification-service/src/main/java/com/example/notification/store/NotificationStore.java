package com.example.notification.store;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationStore {

    private final List<String> notifications =
            new ArrayList<>();

    public void add(String notification) {
        notifications.add(notification);
    }

    public List<String> getAll() {
        return notifications;
    }
}