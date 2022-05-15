package com.zuulproxy.ZuulAPIGateway.controller;


import com.zuulproxy.ZuulAPIGateway.Util.JwtUtil;
import com.zuulproxy.ZuulAPIGateway.model.AuthenticationRequest;
import com.zuulproxy.ZuulAPIGateway.model.AuthenticationResponse;
import com.zuulproxy.ZuulAPIGateway.model.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userdetails;

    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateReq(@RequestBody AuthenticationRequest authReq) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        }
        catch(BadCredentialsException e){
                throw new Exception("Incorrect credentials");
        }
        UserDetails userDetail=userdetails.loadUserByUsername(authReq.getUsername());
        String jwt=jwtUtil.generateToken(userDetail);
        return ResponseEntity.ok(new AuthenticationResponse(jwt,jwtUtil.extractExpiration(jwt)));
    }

    @GetMapping("/helloAll")
    public String helloAll(){
        return "description to all";
    }


    @GetMapping("/helloUser")
    public String helloUser(){
        return "description to users";
    }



    @GetMapping("/helloAdmin")
    public String helloAdmin(){
        return "description to admin";
    }
}
