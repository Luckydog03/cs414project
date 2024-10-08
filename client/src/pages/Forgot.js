import { TextField, Typography, Box } from "@mui/material";
import React from "react";

export default function Forgot() {
    return (
        <Typography variant="p" component="div">
            Forgot You're Hopeless!
            {/* email box for when you forget */}
            <Box
                component="form"
                sx={{
                    "& > :not(style)": { m: 1, width: "25ch" },
                }}
                noValidate
                autoComplete="off"
            >
                <TextField
                    id="outlined-basic"
                    label="Email"
                    variant="outlined"
                />
            </Box>
        </Typography>
    );
}
