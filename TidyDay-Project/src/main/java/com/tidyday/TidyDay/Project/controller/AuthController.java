package com.tidyday.TidyDay.Project.controller;


import com.tidyday.TidyDay.Project.config.JwtProvider;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.repository.UserRepository;
import com.tidyday.TidyDay.Project.request.LoginRequest;
import com.tidyday.TidyDay.Project.response.AuthResponse;
import com.tidyday.TidyDay.Project.service.CustomerUserDetailslmpl;
import com.tidyday.TidyDay.Project.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailslmpl customerUserDetails;


    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception {

        User isUserExist=userRepository.findByEmail(user.getEmail());

        if(isUserExist != null){
            throw new Exception("Email already exist with another account");
        }

        User createdUser=new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail((user.getEmail()));
        createdUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(createdUser);


        subscriptionService.createSubscription(savedUser);



        Authentication authentication =new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setMessage("signup success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse>signing(@RequestBody LoginRequest loginRequest){

        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();

        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= JwtProvider.generateToken(authentication);

        AuthResponse res =new AuthResponse();
        res.setMessage("signing success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails=customerUserDetails.loadUserByUsername(username);
        if (userDetails==null){
            throw  new BadCredentialsException("invalid username");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
    
}
