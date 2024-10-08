import { Stack } from "@mui/material";
import React from "react";
import GameHistory from "./GameHistory";

export default function History() {
    return (
        <Stack spacing={1}>
            <GameHistory opponent={"Casey"} result={"Win"} />
            <GameHistory opponent={"Casey"} result={"Lose"} />
            <GameHistory opponent={"Enzo"} result={"Win"} />
            <GameHistory opponent={"Enzo"} result={"Win"} />
            <GameHistory opponent={"Cyrus"} result={"Lose"} />
            <GameHistory opponent={"Ethan"} result={"Lose"} />
            <GameHistory opponent={"Ethan"} result={"Win"} />
            <GameHistory opponent={"Anthony"} result={"Lose"} />
        </Stack>
    );
}
