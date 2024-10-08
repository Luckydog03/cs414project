import React from "react";
import { Typography } from "@mui/material";
import History from "../components/History";

export default function Account(props) {
    return (
        <>
            <Typography sx={{ mb: 1 }} component={"div"} variant={"h5"}>
                Account Page
            </Typography>
            <Typography sx={{ mb: 2 }} component={"div"} variant={"p"}>
                Email: {props.auth.email}
            </Typography>
            <Typography sx={{ mb: 1 }} component={"div"} variant={"h6"}>
                Game History
            </Typography>
            <History />
        </>
    );
}
