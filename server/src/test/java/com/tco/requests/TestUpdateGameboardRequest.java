package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestUpdateGameboardRequest {

    private UpdateGameboardRequest req;
    String match_id = "0";
    String user_id = "5973";
    String fen = "test";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new UpdateGameboardRequest(match_id, fen, user_id);
        req.buildResponse();
    }

    @Test
    @DisplayName("ethanst: Request type is \"updategameboard\"")
    public void testType() {
        String type = req.getRequestType();
        assertEquals("updategameboard", type);
    }
    
    @Test
    @DisplayName("ethanst: Response is Correct")
    public void testResponse() {
        assertTrue(req.updategameboard(match_id, fen, user_id));
    }
}