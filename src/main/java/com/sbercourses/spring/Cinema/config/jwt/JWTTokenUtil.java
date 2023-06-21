package com.sbercourses.spring.Cinema.config.jwt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JWTTokenUtil {

    public static final long JWT_TOKEN_VALIDATY = 604800000;

    public final String secret = "kekw";

    private static final ObjectMapper objectMapper = new ObjectMapper();









    public String generateToket(final UserDetails payload) {
        return Jwts.builder()
                .setSubject(payload.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDATY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    //Подтверждение токена
    protected Boolean validateToken(final String token,UserDetails userDetails)
    {
        final String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }







    //Проверка на истечение
    protected Boolean isTokenExpired(final String token)
    {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    // Достаём дату из токена
    protected Date getExpirationDateFromToken(String token) {
         return getClaimsFromToken(token,Claims::getExpiration);
    }

    protected String getUserNameFromToken(final String token)
    {
        return getStringValueFromToken(token,"username");
    }

    protected String getUserRoleFromToken(final String token)
    {
        return getStringValueFromToken(token,"user_role");
    }
    public String getStringValueFromToken(final String token,final String key)
    {

        String claim = getClaimsFromToken(token,Claims::getSubject);
        JsonNode claimJson = null;
        try {
            claimJson = objectMapper.readTree(claim);
        }catch (JsonProcessingException e)
        {
            log.error("JWTTOKEN#getUserNameFromToken: {}", e.getMessage());
        }
        if (claimJson!=null)
        {
            return claimJson.get(key).asText();
        }else { return null;}
    }




    private <T> T getClaimsFromToken (final String token, Function<Claims,T> claimResolver)
    {
        final Claims claims=getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


}
