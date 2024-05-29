package com.tidyday.TidyDay.Project.controller;

import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ResponseEntity<Subscription>getUserSubscription(
            @RequestHeader("Authorization")String jwt) throws  Exception{

        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.getUsersSubscription(user.getId());

        return  new ResponseEntity<>(subscription, HttpStatus.OK);
    }

}