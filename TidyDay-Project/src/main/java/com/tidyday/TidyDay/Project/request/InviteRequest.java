package com.tidyday.TidyDay.Project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteRequest {
    private Long scheduleId;
    private String email;
}
