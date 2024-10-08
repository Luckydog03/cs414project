package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReadGameboardRequest {

    private ReadGameboardRequest req;
    String match_id = "0";
    

    @BeforeEach
    public void createConfigurationForTestCases() {
        req = new ReadGameboardRequest(match_id);
        req.buildResponse();
    }

    @Test
    @DisplayName("ethanst: Request type is \"readgameboard\"")
    public void testType() {
        String type = req.getRequestType();
        assertEquals("readgameboard", type);
    }
    
    @Test
    @DisplayName("ethanst: Response is Correct")
    public void testResponse() {
        assertEquals(req.readgameboard(match_id).getFenString(), "test");
    }
}