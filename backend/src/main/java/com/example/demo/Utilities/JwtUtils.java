package com.example.demo.Utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
@NoArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role").toString());
    }

    public String extractUUID(String token) {
        return extractClaim(token, claims -> claims.get("uuid").toString());
    }

    public String extractEmail(String token) {
        /*log.warn(token);
        log.info(token.substring(7));
        log.warn(token.substring(8));*/
        return extractClaim(token, claims -> claims.get("email").toString());
    }

    public Boolean validateToken(String token) {
        try {
            return !extractClaim(token, Claims::getExpiration).before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String generatePersistentJWT(String email)
    {
        Map<String, String> claims = new HashMap<>();
        claims.put("email", email);
        return createToken(claims, 60 *  60 * 24 * 30);
    }

    public String generateSessionJWT(String role)
    {
        Map<String, String> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("uuid", UUID.randomUUID().toString());
        return createToken(claims, 60 * 60 * 24);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    private String createToken(Map<String, String> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
