package com.tidyday.TidyDay.Project.controller;


import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.repository.UserRepository;
import com.tidyday.TidyDay.Project.service.CustomerUserDetailslmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerUserDetailslmpl customeUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<User>createUserHandler(@RequestBody User user) throws Exception {

        User isUserExist=userRepository.findByEmail(user.getEmail());

        if(isUserExist!=null){
            throw new Exception("Email already exist with another account");
        }
        User createdUser=new User();
        createdUser.setEmail((user.getEmail()));
        createdUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(createdUser);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


}
