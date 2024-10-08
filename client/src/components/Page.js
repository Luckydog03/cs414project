import { Outlet } from "react-router-dom";
import React from "react";
import Container from "@mui/material/Container";
import { Box, Paper, Typography } from "@mui/material";
import Appbar from "../components/Appbar";

export default function Page(props) {
    return (
        <Container>
            <Appbar {...props} />
            <Paper>
                <Container sx={{ pb: 5 }}>
                    <Box sx={{ pb: 3 }} />
                    <Typography variant={"h4"} component={"div"}>
                        Dungeons and Debuggers
                    </Typography>

                    <Box sx={{ pb: 3 }} />
                    <Outlet />
                </Container>
            </Paper>
        </Container>
    );
}
