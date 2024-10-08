package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReadInviteRequest {

    private ReadInviteRequest req;
    String user_id = "aragorn";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new ReadInviteRequest(user_id);
        req.buildResponse();
    }

    @Test
    @DisplayName("enzob: Request type is \"readmatch\"")
    public void testType() {
        String type = req.getRequestType();
        assertEquals("readinvite", type);
    }
    
    @Test
    @DisplayName("enzob: Response is Correct")
    public void testResponse() {
        assertTrue(req.readinvites(user_id).size() == 0);
    }
}