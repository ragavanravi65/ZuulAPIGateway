package com.zuulproxy.ZuulAPIGateway.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String accessToken;
    private Date expiry;

}
