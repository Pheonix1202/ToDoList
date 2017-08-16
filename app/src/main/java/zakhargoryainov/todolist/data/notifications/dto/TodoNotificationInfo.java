package zakhargoryainov.todolist.data.notifications.dto;

import lombok.Data;

/**
 * Created by Захар on 15.08.2017.
 */

public @Data class TodoNotificationInfo {

    private String title;
    private String message;
    private long timestamp;

    public TodoNotificationInfo(String title, String message, long timestamp) {
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
    }
}
