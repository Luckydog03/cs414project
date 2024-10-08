package com.tco.game;

import java.util.*;
import java.io.*;

import com.tco.requests.SendInviteRequest;
import com.tco.requests.ReadInviteRequest;
import com.tco.player.User;


public class Invitation {
    private String sender_id;
    private String receiver_id;
    private String invite_id;
    private String sender_email;

    public Invitation(String sender_id, String receiver_id, String invite_id, String sender_email){
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.invite_id = invite_id;
        this.sender_email = sender_email;
    }
}
