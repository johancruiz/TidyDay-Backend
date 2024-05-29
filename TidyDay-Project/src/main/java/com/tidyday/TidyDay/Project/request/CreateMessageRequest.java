package com.tidyday.TidyDay.Project.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

    private Long SenderId;

    private String content;

    private Long scheduleId;

}
