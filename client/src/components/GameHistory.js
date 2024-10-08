import { Chip, Stack } from "@mui/material";
import React from "react";

export default function GameHistory(props) {
    return (
        <Stack direction={"row"} spacing={1}>
            <Chip color="primary" label={props.opponent} />
            <Chip
                color={props.result === "Win" ? "success" : "error"}
                label={props.result}
            />
        </Stack>
    );
}
