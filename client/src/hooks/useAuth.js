import { useEffect, useState } from "react";
import useWebSocket from "./useWebSocket";
import useLocalStorage from "use-local-storage";
import { sendLoginRequest, sendRegisterRequest } from "../utils/requests";
import sendReqWithErrorHandling from "../utils/sendReqWithErrorHandling";
import useInvite from "./useInvite";
import useUserList from "./useUserList";

export default function useAuth(serverUrl, websocketUrl, showMessage) {
    const [loggedIn, setLoggedin] = useState(false);
    const [email, setEmail] = useState("");
    const [userID, setUserID] = useState(-1);
    const [matchID, setMatchID] = useState("");

    const invites = useInvite(serverUrl, userID, showMessage, setMatchID);
    const [userList, getUserList] = useUserList(serverUrl, userID, showMessage);

    const [savedEmail, setSavedEmail] = useLocalStorage("savedEmail", "");
    const [savedUser_id, setSavedUser_id] = useLocalStorage("savedUser_id", "");
    const websocket = new useWebSocket(websocketUrl, showMessage);

    useEffect(() => {
        // is user logged in? Check storage
        if (savedEmail && savedUser_id && !loggedIn) {
            updateStateToLoggedIn(savedEmail, savedUser_id);
        }
    }, []);

    const updateStateToLoggedIn = (email, user_id) => {
        setUserID(user_id);
        setEmail(email);
        setSavedEmail(email);
        setSavedUser_id(user_id);
        setLoggedin(true);
        websocket.connect().then(() => {
            websocket.sendMsg(`user logged in with email ${email}`);
        });
    };

    const context = {
        updateStateToLoggedIn,
        serverUrl,
        showMessage,
        setLoggedin,
        setEmail,
        websocket,
        userID,
    };

    const loginUser = (username, password) =>
        _loginUser(username, password, context);

    const registerUser = (username, password) =>
        _registerUser(username, password, context);

    function logout() {
        setLoggedin(false);
        setEmail("");
        setSavedEmail("");
        setSavedUser_id("");
    }

    return [
        {
            invites: invites,
            registerUser,
            loginUser,
            loggedIn,
            logout,
            email,
            userList,
            getUserList,
            matchID,
        },
    ];
}

function _registerUser(email, password, context) {
    const { serverUrl } = context;

    sendReqWithErrorHandling(
        sendRegisterRequest(email, password, serverUrl),
        "response",
        context,
    );
}

function _loginUser(email, password, context) {
    const { serverUrl } = context;
    sendReqWithErrorHandling(
        sendLoginRequest(email, password, serverUrl),
        "response",
        context,
    );
}
