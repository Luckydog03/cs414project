import { sendAPIRequest } from "./restfulAPI";

export async function sendRegisterRequest(email, password, serverUrl) {
    return sendAPIRequest(
        {
            email: email,
            password: password,
            requestType: "register",
        },
        serverUrl,
    );
}

export async function sendLoginRequest(email, password, serverUrl) {
    return sendAPIRequest(
        {
            email: email,
            password: password,
            requestType: "login",
        },
        serverUrl,
    );
}

export async function sendGetInviteRequest(userID, serverUrl) {
    return sendAPIRequest(
        {
            receiver_id: userID.toString(),
            requestType: "readinvite",
        },
        serverUrl,
    );
}

export async function sendSendInviteRequest(
    userID,
    target_user_email,
    serverUrl,
) {
    return sendAPIRequest(
        {
            sender_user_id: userID.toString(),
            receiver_email: target_user_email.toString(),
            requestType: "sendinvite",
        },
        serverUrl,
    );
}

export async function sendAcceptInviteRequest(inviteID, serverUrl) {
    return sendAPIRequest(
        {
            invite_id: inviteID.toString(),
            requestType: "acceptinvite",
        },
        serverUrl,
    );
}

export async function sendRejectInviteRequest(inviteID, serverUrl) {
    return sendAPIRequest(
        {
            invite_id: inviteID.toString(),
            requestType: "rejectinvite",
        },
        serverUrl,
    );
}

export async function sendGetUserListRequest(userID, serverUrl) {
    return sendAPIRequest(
        {
            receiver_id: userID.toString(),
            requestType: "readuser",
        },
        serverUrl,
    );
}
