package com.tco.requests;

// import com.tco.requests.Request;

import com.google.gson.JsonObject;

import org.java_websocket.WebSocket;
// import org.java_websocket.handshake.ClientHandshake;
// import org.java_websocket.server.WebSocketServer;

public class WebSocketRequest {
    
    // Additional fields and methods to handle WebSocket specifics
    protected JsonObject messageData;
    protected String requestType;

    public WebSocketRequest(JsonObject messageData) {
        this.messageData = messageData;
        // Extract the type from the JSON message data
        this.requestType = messageData.has("type") ? messageData.get("type").getAsString() : null;
    }
}