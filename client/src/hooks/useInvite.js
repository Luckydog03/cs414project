import { useState } from "react";
import {
    sendAcceptInviteRequest,
    sendGetInviteRequest,
    sendRejectInviteRequest,
    sendSendInviteRequest,
} from "../utils/requests";
import sendReqWithErrorHandling from "../utils/sendReqWithErrorHandling";

export default function useInvite(serverUrl, userID, showMessage, setMatchID) {
    const [invites, setInvites] = useState([]);
    const [startGame, setStartGame] = useState(false);

    const context = {
        setMatchID,
        invites,
        setInvites,
        serverUrl,
        userID,
        showMessage,
        setStartGame,
    };

    const sendInvite = (target_user_email) =>
        _sendInvite(context, target_user_email);

    const acceptInvite = (invite_id) => _acceptInvite(context, invite_id);

    const rejectInvite = (invite_id) => _rejectInvite(context, invite_id);

    const getInvites = () => _getInvites(context);

    return {
        acceptInvite,
        rejectInvite,
        sendInvite,
        invites,
        getInvites,
        startGame,
    };
}

function _getInvites(context) {
    const { serverUrl, userID, setInvites } = context;
    sendReqWithErrorHandling(
        sendGetInviteRequest(userID, serverUrl),
        "invite_list",
        context,
        setInvites,
    );
}

function _sendInvite(context, target_user_email) {
    const { serverUrl, userID } = context;
    sendReqWithErrorHandling(
        sendSendInviteRequest(userID, target_user_email, serverUrl),
        "response",
        context,
    );
}

function _acceptInvite(context, inviteID) {
    const { serverUrl, setStartGame, setMatchID } = context;
    sendReqWithErrorHandling(
        sendAcceptInviteRequest(inviteID, serverUrl),
        "match_id",
        context,
        (id) => {
            setStartGame(true);
            setMatchID(id);
        },
    );
}

function _rejectInvite(context, inviteID) {
    const { serverUrl, showMessage } = context;
    sendReqWithErrorHandling(
        sendRejectInviteRequest(inviteID, serverUrl),
        "response",
        context,
        () => {
            showMessage("Rejected!");
        },
    );

    setTimeout(_getInvites(context), 200);
}
