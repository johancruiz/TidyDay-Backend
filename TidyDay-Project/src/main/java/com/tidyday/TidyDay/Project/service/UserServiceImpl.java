package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.config.JwtProvider;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);

        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("User nor found");
        }
        return null;
    }

    @Override
    public User findByUserId(Long userId) throws Exception {
        Optional<User> optionalUser=userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new Exception("User not found");
        }
        return null;
    }

    @Override
    public User updateUserProjectSize(User user, int number) {
        return null;
    }
}
