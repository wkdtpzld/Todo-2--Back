package com.todo.todoP.Security;

import com.todo.todoP.Entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 토큰 키

    public String create(Member member){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        int tokenExpire = 180000;
        calendar.add(Calendar.MILLISECOND, tokenExpire);
        Date expireDate = calendar.getTime();

        return Jwts.builder()
                .signWith(key)

                .setSubject(member.getId().toString())
                .setIssuer("todo app")
                .setIssuedAt(date)
                .setExpiration(expireDate)

                .compact();
    }

    public String validateToken(String authToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();

        return claims.getSubject();
    }

}
