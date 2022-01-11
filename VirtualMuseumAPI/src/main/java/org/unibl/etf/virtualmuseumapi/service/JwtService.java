package org.unibl.etf.virtualmuseumapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.property.JwtProperties;

import java.sql.Date;
import java.time.Instant;

@Service
@Slf4j
public class JwtService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long duration;

    public JwtService(JwtProperties properties) {
        duration = properties.getDuration().getSeconds();
        algorithm = Algorithm.HMAC512(properties.getSecret());
        verifier = JWT.require(algorithm).build();
    }

    public String createToken(String username) {
        return JWT
                .create()
                .withSubject(username)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(duration)))
                .sign(algorithm);
    }

    public String validateToken(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            log.warn(exception.getMessage(), exception);
            return null;
        }
    }
}
