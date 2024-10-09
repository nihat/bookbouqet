package com.bookbouqet.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class JWTService {

    private long jwtExpiration;
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private  String generateToken(HashMap<String, Object> claims, UserDetails userDetails) {

        return buildToken(claims,userDetails,jwtExpiration);
    }

    private String buildToken(HashMap<String, Object> extraClaims,
                              UserDetails userDetails,
                              long jwtExpiration) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder().claims(extraClaims).
                subject(userDetails.getUsername()).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + jwtExpiration)).
                claim("authorities",authorities).
                signWith(getSignKey()).
                compact();

    }

    private Key getSignKey() {

    }
}
