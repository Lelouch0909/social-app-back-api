package com.lontsi.devsocialmedia.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.lontsi.devsocialmedia.config.JwtProvider;
import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.User;
import com.lontsi.devsocialmedia.models.Verification;
import com.lontsi.devsocialmedia.repository.UserRepository;
import com.lontsi.devsocialmedia.response.AuthResponse;
import com.lontsi.devsocialmedia.services.CustomUserDetailsServiceImpl;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired

    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetails;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{


        System.out.println(user.toString());
        String email = user.getEmail();
        String password = user.getPassword();
        String fulName = user.getFullName();
        String birthDate = user.getBirthDate();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null) {
            throw new UserException("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fulName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(birthDate);
        createdUser.setVerification(new Verification());

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,true);
        return new ResponseEntity<AuthResponse>(res, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user){
        String username = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticate(username,password);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,true);
        return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);

    }



    private Authentication authenticate(String username,String password){
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid username...");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
