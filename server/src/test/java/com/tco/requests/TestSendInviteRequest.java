import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tco.requests.SendInviteRequest;

public class TestSendInviteRequest {
    @Test
    @DisplayName("enzob: test")
    public void basicTest() {
        SendInviteRequest req = new SendInviteRequest("1235", "123");
    }

    @Test
    @DisplayName("cjstumpf: test send")
    public void sendInvite() {
        SendInviteRequest req = new SendInviteRequest("722485", "casey");
        req.sendInvitation();
    }

    @Test
    @DisplayName("enzob: build")
    public void build() {
        SendInviteRequest req = new SendInviteRequest("722485", "casey");
        req.buildResponse();
    }
}
