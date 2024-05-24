package com.tidyday.TidyDay.Project.repository;

import com.tidyday.TidyDay.Project.modal.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);

}
