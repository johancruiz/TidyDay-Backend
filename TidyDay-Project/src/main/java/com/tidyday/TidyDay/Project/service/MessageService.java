package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId, Long scheduleId, String content) throws Exception;

    List<Message> getMessageByProjectId(Long projectId ) throws Exception;
}
