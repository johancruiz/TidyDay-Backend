package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.User;

public interface UserService {
    User findUserProfileByJwt(String jwt)throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findByUserId(Long userId) throws Exception;

    User updateUserProjectSize(User user, int number);
}
