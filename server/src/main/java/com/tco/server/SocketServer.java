package com.tco.server;

import com.tco.misc.ErrorResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

public class SocketServer extends WebSocketServer {
	private static Map<String, WebSocket> conns = new ConcurrentHashMap<>();
	//private static Map<String, Match> activeMatches = new ConcurrentHashMap<>();

    public SocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Connection established.");
		broadcast( "new connection: " + handshake.getResourceDescriptor() );
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }
    @Override
	public void onStart() {
		System.out.println("server started.");
	}
    @Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		conns.remove(conn);
		System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		try{
			// retireve message from client
			JsonObject jsonMessage = new Gson().fromJson(message, JsonObject.class);
			String type = jsonMessage.has("type") ? jsonMessage.get("type").getAsString() : null;
			String recipient_id = jsonMessage.has("recipient_id") ? jsonMessage.get("recipient_id").getAsString() : null;
			String game_id= jsonMessage.has("game_id") ? jsonMessage.get("game_id").getAsString() : null;
			String invite_id= jsonMessage.has("invite_id") ? jsonMessage.get("invite_id").getAsString() : null;
			String user_id= jsonMessage.has("user_id") ? jsonMessage.get("user_id").getAsString() : null;
			String user_email= jsonMessage.has("user_email") ? jsonMessage.get("user_email").getAsString() : null;

			// woop woop message handling
			switch (type) {
				// our first socket endpoint
	
				case "game":
					sendMessageToClient(recipient_id, "game", game_id);
				case "invite":
					sendMessageToClient(recipient_id, "invite", invite_id);
				case "connect":
					conns.put(user_id, conn);
					System.out.println("connected user number " + user_id + " with email " + user_email + " to websocket.");
				default:
					ErrorResponse errorResponse = new ErrorResponse("Invalid request type");
					// Send a JSON-formatted error message in the case of request not existing
					conn.send(new Gson().toJson(errorResponse.toJson()));
					return;
			}
		} catch (JsonSyntaxException e) {
			conn.send(new Gson().toJson(new ErrorResponse("Bad JSON format").toJson()));
		} catch (Exception e) {
			// Catch all for any other exceptions not specifically caught above
			conn.send(new Gson().toJson(new ErrorResponse("Internal server error").toJson()));
		}

	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
	}

	//helper functions
    public void sendMessageToClient(String client_id, String type, String instance_id) {
        WebSocket conn = conns.get(client_id);
        if (conn != null) {
            JsonObject json = new JsonObject();
        	json.addProperty("type", type);
        	json.addProperty("id", instance_id);
            conn.send(json.toString());
        }
    }
   
    //database update functions
    //joinMatch(String MatchID, String client_id): adds selected match to matches list. if match exists in matches, add player to match as player2. if both players joined, start match. Send fenstring data to both clients.
	//TakeTurn(String MatchID, String client_id): Take turn, validating to see if player can take turn. Once turn is taken, return fenstring result to other client.
    //quitAndSave(String MatchID, String client_id): remove match from matches and update database with new fenstring.

}