package tdtu.vn.figure_shop.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenProvider {
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String SECRET_KEY = "sakefigureshopsecret";

    public String createToken(Authentication authentication){
        String email = authentication.getName();

        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("email", email)
                .compact();
    }
    public String getUserEmailFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

}
