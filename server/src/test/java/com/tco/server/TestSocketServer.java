import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tco.server.SocketServer;
import java.net.InetSocketAddress;

public class TestSocketServer {
	SocketServer server = new SocketServer(new InetSocketAddress("localhost", 12346));

	@Test
	@DisplayName("enzob: test send")
	public void test() {
		server.sendMessageToClient("1234", "message", "asdf");
	}
}
