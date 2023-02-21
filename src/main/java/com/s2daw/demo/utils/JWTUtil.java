package com.s2daw.demo.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * @author Mahesh, modificado por Julio León para evitar métodos depreciados y mejorar un poco el código
 */
@Component
public class JWTUtil {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory
            .getLogger(JWTUtil.class);


    public String create(String id, String subject) {


        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(toKey(key),SignatureAlgorithm.HS256);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public String getValue(String jwt) {
        Claims claims=obtenerClaims(jwt);
        if (claims==null) return null;
        return claims.getSubject();
    }

    public String getKey(String jwt) {
        Claims claims=obtenerClaims(jwt);
        if (claims==null) return null;
        return claims.getId();
    }

    // Clases de apoyo
    private Key toKey(String key){
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }
    // Valida jws y obtiene claims
    private Claims obtenerClaims(String jws){
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(toKey(key))
                    .build()
                    .parseClaimsJws(jws)
                    .getBody();
        }
        catch (JwtException exp) {
            return null;
        }
        return claims;
    }

}