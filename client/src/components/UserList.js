import React, { useEffect, useState } from "react";
import { Box, Button, Typography } from "@mui/material";
import Autocomplete from "@mui/material/Autocomplete";
import TextField from "@mui/material/TextField";

export default function UserList(props) {
    useEffect(() => {
        props.auth.getUserList();
    }, []);

    const [selectedUser, setSelectedUser] = useState("");

    const sendInvite = () => {
        if (selectedUser !== "") {
            props.auth.invites.sendInvite(selectedUser);
            props.showMessage("Invite sent!", "success");
        } else {
            props.showMessage("Please select a user!", "warning");
        }
    };

    return (
        <div>
            <Typography sx={{ mb: 1 }} component={"div"} variant={"h5"}>
                Send an Invite
            </Typography>
            <Box sx={{ mb: 2, display: "flex", flexDirection: "row" }}>
                <Autocomplete
                    disablePortal
                    id="userSearch"
                    onInputChange={(e, value) => setSelectedUser(value)}
                    options={props.auth.userList.map((user) => {
                        return user["email"];
                    })}
                    sx={{ width: 300 }}
                    renderInput={(params) => (
                        <TextField {...params} label="Search Player" />
                    )}
                />
                <Button
                    onClick={sendInvite}
                    sx={{ ml: 1 }}
                    variant={"contained"}
                >
                    Send
                </Button>
            </Box>
        </div>
    );
}
