import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tco.requests.RejectInviteRequest;

public class TestRejectInviteRequest {
    @Test
    @DisplayName("cjstumpf: test")
    public void basicTest() {
        RejectInviteRequest req = new RejectInviteRequest("1235");
    }

    @Test
    @DisplayName("cjstumpf: test send")
    public void sendInvite() {
        RejectInviteRequest req = new RejectInviteRequest("1235");
        req.rejectInvite();
    }

    @Test
    @DisplayName("enzob: build")
    public void build() {
        RejectInviteRequest req = new RejectInviteRequest("1235");
        req.buildResponse();
    }
}
