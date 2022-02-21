package com.todo.todoP.Security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenProviderTest {

    @Autowired TokenProvider tokenProvider;

//    @Test
//    public void createTokenTest(){
//        String token = tokenProvider.create();
//        System.out.println(token);
//    }
//
//    @Test
//    public void validationTokenTest(){
//        String token = tokenProvider.create();
//        tokenProvider.validateToken(token);
//    }
}