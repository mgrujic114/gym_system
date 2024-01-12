package sk2.trainingservice.secutiry.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generate(Claims claims);

    Claims parseToken(String jwt);
}
