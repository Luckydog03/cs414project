import React from "react";
import Board from "../components/Board";
import { Typography } from "@mui/material";

export default function Game(props) {
    return (
        <div>
            <p>Game Page </p>

            {props.auth.matchID ? (
                <Typography sx={{ mb: 1 }} component={"div"} variant={"h5"}>
                    Match Started with ID {props.auth.matchID}
                </Typography>
            ) : (
                <></>
            )}

            <Board />
        </div>
    );
}
