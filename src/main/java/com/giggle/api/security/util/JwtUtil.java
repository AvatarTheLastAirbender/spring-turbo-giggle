package com.giggle.api.security.util;


import com.giggle.api.model.component.CustomUserDetails;
import com.giggle.api.service.ConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    ConfigProperties configProp;

    public String generateToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, customUserDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        //  todo Change Session Time If Needed
        int SessionTimeLimit = 1000 * 60 * 60 * 10;
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SessionTimeLimit))
                .signWith(SignatureAlgorithm.HS512, configProp.getConfigValue("spring.security.secret-key")).compact();
    }

    public Boolean validateToken(String token, CustomUserDetails customUserDetails) {
        final String username = extractUserName(token);
        return (username.equals(customUserDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser().setSigningKey(configProp.getConfigValue("spring.security.secret-key")).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}

