import React, { useState } from "react";
import { Box, Button, Typography } from "@mui/material";
import ManagedTextField from "../components/ManagedTextField";
import { Navigate } from "react-router-dom";

export default function Register(props) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    if (props.auth.loggedIn) {
        return <Navigate to="/game" replace />;
    }

    return (
        <Typography variant="p" component="div">
            Register
            {/* Box for display name, name, password, confirm password  */}
            <Box
                component="div"
                sx={{
                    "& > :not(style)": { mt: 2, width: "25ch" },
                    mb: 1,
                }}
                noValidate
                autoComplete="off"
            >
                <ManagedTextField value={[email, setEmail]} label={"Email"} />
                <ManagedTextField
                    value={[password, setPassword]}
                    label={"Password"}
                    password={true}
                />

                <Button
                    onClick={() => {
                        props.auth.registerUser(email, password);
                    }}
                    color="secondary"
                    variant="contained"
                >
                    Submit
                </Button>
            </Box>
        </Typography>
    );
}
