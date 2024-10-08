import React, { useEffect } from "react";
import {
    Button,
    ListItem,
    ListItemText,
    Paper,
    Typography,
} from "@mui/material";
import UserList from "../components/UserList";
import { List } from "reactstrap";
import "./css/Invite.css";
import { Navigate } from "react-router-dom";

export default function Invite(props) {
    useEffect(() => {
        props.auth.invites.getInvites();
    }, []);

    if (props.auth.invites.startGame) return <Navigate to="/game" replace />;

    return (
        <div>
            <UserList {...props} />

            <Typography sx={{ mb: 1 }} component={"div"} variant={"h5"}>
                Your Invites
            </Typography>

            {props.auth.invites.invites.length > 0 ? (
                <Paper sx={{ mt: 3, width: 1 / 3 }} elevation={8}>
                    <List>
                        {props.auth.invites.invites.map((invite) => {
                            return (
                                <ListItem key={invite["invite_id"]} divider>
                                    <ListItemText
                                        primary={invite["sender_email"]}
                                    />
                                    <Button
                                        color="success"
                                        onClick={() => {
                                            props.auth.invites.acceptInvite(
                                                invite["invite_id"],
                                            );
                                        }}
                                    >
                                        Accept
                                    </Button>
                                    <Button
                                        color="error"
                                        onClick={() => {
                                            props.auth.invites.rejectInvite(
                                                invite["invite_id"],
                                            );
                                        }}
                                    >
                                        Reject
                                    </Button>
                                </ListItem>
                            );
                        })}
                    </List>
                </Paper>
            ) : (
                <></>
            )}
        </div>
    );
}
