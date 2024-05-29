package com.tidyday.TidyDay.Project.request;

import lombok.Data;

@Data
public class CreateMessageRequest {
    private Long senderId;
    private String content;
    private Long scheduleId;
}
