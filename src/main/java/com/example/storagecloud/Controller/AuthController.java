package com.example.storagecloud.Controller;

import com.example.storagecloud.Dto.request.AuthRQ;
import com.example.storagecloud.Dto.response.AuthRP;
import com.example.storagecloud.Exception.BadCredentialException;
import com.example.storagecloud.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(AuthRQ authRq){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRq.getUsername(), authRq.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialException("Такого пользователя не существует!");
        }

        UserDetails user = userDetailsService.loadUserByUsername(authRq.getUsername());
        final String token  = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthRP(token));

    }

    @PostMapping("/logout")
    public ResponseEntity<?>logout(@RequestHeader("auth-token") String authToken){
        return ResponseEntity.ok(HttpStatus.OK);
    }
}



