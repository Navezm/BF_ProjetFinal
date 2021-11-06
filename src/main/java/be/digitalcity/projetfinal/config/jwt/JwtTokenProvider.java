package be.digitalcity.projetfinal.config.jwt;

import static be.digitalcity.projetfinal.config.SecurityConstants.*;

import be.digitalcity.projetfinal.models.entity.Role;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final UserService service;

    public JwtTokenProvider(UserService service) {
        this.service = service;
    }

    public String createToken(User user){
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt( new Date(System.currentTimeMillis() + EXPIRATION_TIME) )
                .withClaim("roles", user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
                )
                .sign(Algorithm.HMAC512(JWT_KEY));

        return TOKEN_PREFIX+token;
    }

    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_KEY);
        if(token != null && token.startsWith(TOKEN_PREFIX))
            return token.substring(TOKEN_PREFIX.length());

        return null;
    }

    public boolean validateToken(String token){
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JWT_KEY))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            String username = decodedJWT.getSubject();
            Date exp = decodedJWT.getExpiresAt();

            return username != null && exp != null && exp.after(new Date(System.currentTimeMillis()));
        } catch (JWTVerificationException ex){
            return false;
        }
    }

    public Authentication getAuthentication(String token){
        String username = JWT.decode(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        UserDetails userDetails = service.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

}
