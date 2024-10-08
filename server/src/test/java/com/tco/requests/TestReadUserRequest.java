package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReadUserRequest {

    private ReadUserRequest req;
    String user_id = "banana";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new ReadUserRequest(user_id);
        req.buildResponse();
    }

    @Test
    @DisplayName("ethanst: Request type is \"readuser\"")
    public void testType() {
        String type = req.getRequestType();
        assertEquals("readuser", type);
    }
    
    @Test
    @DisplayName("ethanst: Response is Correct")
    public void testResponse() {
        assertTrue(req.readusers(user_id).size() > 0);
    }
}