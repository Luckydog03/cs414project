import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tco.requests.WebSocketRequest;
import com.google.gson.JsonObject;

public class TestWebSocketRequest {
    @Test
    @DisplayName("cjstumpf: test web socket req")
    public void basicTest() {
        WebSocketRequest req = new WebSocketRequest(new JsonObject());
    }
}
