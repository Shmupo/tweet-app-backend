package com.tweeter.user_service.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component // flag class as a Spring-managed component
public class JWTTokenProvider {
    private String jwtSecret = "dGVzdGtleWZvcnRlc3Rhc3Rlc3QzNTY3ODl0eXo3Y3d1cGZqbnU2d2xmbXA3NTMzMTI1Ng"; // for setting hash token
    private Long jwtExpirationDate = 604800L; // in ms = 7 days
    // generate token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    // get username from jwt token
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate token
    // - check expiry
    // - check invalid token
    // - check unsupported token (altered format)
    // - check jwt claim is null or empty
    public boolean validateToken(String token) {
        System.out.println("Recieved Token:" + token);

        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

}
