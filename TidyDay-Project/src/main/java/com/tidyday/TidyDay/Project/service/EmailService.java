package com.tidyday.TidyDay.Project.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmailWithToken(String userEmail, String link) throws Exception;
}
