package com.tidyday.TidyDay.Project.request;


import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
public class IssueRequest {
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long scheduleId;
    private LocalDate dueDate;
}
