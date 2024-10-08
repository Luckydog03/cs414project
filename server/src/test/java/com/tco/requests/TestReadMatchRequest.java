package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReadMatchRequest {

    private ReadMatchRequest req;
    String user_id = "aragorn";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new ReadMatchRequest(user_id);
        req.buildResponse();
    }

    @Test
    @DisplayName("ethanst: Request type is \"readmatch\"")
    public void testType() {
        String type = req.getRequestType();
        assertEquals("readmatch", type);
    }
    
    @Test
    @DisplayName("ethanst: Response is Correct")
    public void testResponse() {
        assertTrue(req.readmatches(user_id).size() == 0);
    }
}