import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tco.requests.AcceptInviteRequest;
import com.tco.requests.SendInviteRequest;

import com.tco.database.Database;

public class TestAcceptInviteRequest {
    @Test
    @DisplayName("enzob: test")
    public void basicTest() {
        AcceptInviteRequest req = new AcceptInviteRequest("722485");
    }

    @Test
    @DisplayName("enzob: build")
    public void buildTest() {
        AcceptInviteRequest req = new AcceptInviteRequest("722485");
        req.buildResponse();
    }

    @Test
    @DisplayName("enzob: create match")
    public void matchTest() {
        AcceptInviteRequest req = new AcceptInviteRequest("722485");

        Database database = Database.getInstance();

        req.createMatch(database.getConnection(), "722485","722485");
    }
}
