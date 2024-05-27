package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Invitation;
import com.tidyday.TidyDay.Project.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long scheduleId) throws Exception {

        String invitationToken= UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setScheduleId(scheduleId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        String invitationLink="http://localhost:5173/accept_invitation?token" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);

    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if (invitation == null) {
            throw new Exception("Invitation not found");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userMail) {
        Invitation invitation=invitationRepository.findByEmail(userMail);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation=invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);

    }
}
