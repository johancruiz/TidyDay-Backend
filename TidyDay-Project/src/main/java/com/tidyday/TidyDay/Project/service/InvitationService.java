package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Invitation;
import org.springframework.stereotype.Service;


public interface InvitationService {
    public void sendInvitation(String email, Long scheduleId) throws Exception;

    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String userMail);

    void deleteToken(String token);
}
