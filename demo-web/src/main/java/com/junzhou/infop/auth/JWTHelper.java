package com.junzhou.infop.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTHelper {
    private final static String SECRET = "MY_OWN_SECRET";

    private final JWTVerifier jwtVerifier;
    private final Algorithm algorithm;

    public JWTHelper() {
        jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        algorithm = Algorithm.HMAC256(SECRET);
    }

    public String generateToken(String userId, Date expireAt) {
        return JWT.create().withSubject(userId)
                .withExpiresAt(expireAt)
                .withIssuer("infoP")
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID().toString()).sign(algorithm);
    }

    public String generateToken(String userId, Date expireAt, String uuid) {
        return JWT.create().withSubject(userId)
                .withExpiresAt(expireAt)
                .withIssuer("infoP")
                .withIssuedAt(new Date())
                .withJWTId(uuid).sign(algorithm);
    }


    public String validateToken(String token) {
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getSubject();
        } catch (Exception exception) {
            return null;
        }

    }

    public DecodedJWT validateToken1(String token) {
        try {

            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT;
        } catch (Exception exception) {
            return null;
        }

    }


}
