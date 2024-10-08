import { useState } from "react";
import { sendGetUserListRequest } from "../utils/requests";
import sendReqWithErrorHandling from "../utils/sendReqWithErrorHandling";

export default function useUserList(serverUrl, userID, showMessage) {
    const [userList, setUserList] = useState([]);

    const getUserList = () => {
        sendReqWithErrorHandling(
            sendGetUserListRequest(userID, serverUrl),
            "user_list",
            { showMessage },
            setUserList,
        );
    };

    return [userList, getUserList];
}
