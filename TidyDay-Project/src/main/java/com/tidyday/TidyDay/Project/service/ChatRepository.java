package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
