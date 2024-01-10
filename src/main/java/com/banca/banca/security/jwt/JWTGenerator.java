package com.banca.banca.security.jwt;

import com.banca.banca.security.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {

    /**
     * Metodo che ci permette di generare un jwt token con le dipendenze io.jsonwebtoken
     * Abbiamo settato lo username, la scadenze e la secret
     * che troviamo nella classi di constanti che abbiamo creato noi SecurityConstant
     *
     * @param
     * @return
     */
    public String generateToken (String username, String fiscalCode) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setId(fiscalCode)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstant.JWT_SECRET)
                .compact();
        return token;
    }

    /**
     * Metodo per prendere l'username dal JWT
     *
     * @param token
     * @return
     */
    public String getUsernameFromJWT (String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstant.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Metodo per recuperare il fiscalcode dal JWT
     *
     * Dal momento che il token ha il presiffo "Bearer " dobbiamo splittare la stringa in due tramite lo spazio
     * e recuperare la seconda parte che ha indice 1
     *
     * @param token
     * @return
     */
    public String getFiscalCodeFromJWT (String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstant.JWT_SECRET)
                .parseClaimsJws(token.split(" ")[1])
                .getBody();

        return claims.getId();
    }

    public boolean validateToken (String token) {

        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstant.JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect!");
        }
    }
}
