package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestLoginRequest {

    private LoginRequest conf;
    String email = "banana@gmail.com";
    String password = "banana";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        conf = new LoginRequest(email, password);
        conf.buildResponse();
    }

    @Test
    @DisplayName("base: Request type is \"login\"")
    public void testType() {
        String type = conf.getRequestType();
        assertEquals("login", type);
    }
    
    @Test
    @DisplayName("base: Response is Correct")
    public void testResponse() {
        int response = conf.loginUser(email, password);
        assertNotEquals(-1, response);
    }
}