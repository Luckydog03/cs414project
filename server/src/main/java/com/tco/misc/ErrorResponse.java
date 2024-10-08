package com.tco.misc; // use the package where ErrorResponse should belong

import com.google.gson.JsonObject;

public class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public JsonObject toJson() {
        JsonObject response = new JsonObject();
        response.addProperty("error", error);
        return response;
    }
}