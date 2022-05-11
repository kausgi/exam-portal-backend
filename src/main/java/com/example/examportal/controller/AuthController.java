package com.example.examportal.controller;

import com.example.examportal.config.JwtUtil;
import com.example.examportal.model.JWTRequest;
import com.example.examportal.model.JWTToken;
import com.example.examportal.model.User;
import com.example.examportal.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@Transactional
public class AuthController {

    final AuthenticationManager authenticationManager;
    final JwtUtil jwtUtil;
    final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/generate_token")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch(UsernameNotFoundException e){
            throw new Exception("User Not Found");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTToken(token));
    }

    private void authenticate(String user_name, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user_name,password));
        }catch (DisabledException e){
            throw new DisabledException("User is disabled");
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    @GetMapping("/current_user")
    public User getCurrentUser(Principal principal){
        return (User)this.userDetailsService.loadUserByUsername(principal.getName());
    }

}
