package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestRegisterRequest {

    private RegisterRequest conf;
    String email = "banana@gmail.com";
    String password = "banana";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        conf = new RegisterRequest(email, password);
        conf.buildResponse();
    }

    @Test
    @DisplayName("ethanst: Request type is \"register\"")
    public void testType() {
        String type = conf.getRequestType();
        assertEquals("register", type);
    }
    
    @Test
    @DisplayName("ethanst: Response is Correct")
    public void testResponse() {
        assertFalse(conf.addUser(email, password));
    }
}