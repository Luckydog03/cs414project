import { TextField, Typography, Box, Button } from "@mui/material";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import ManagedTextField from "../components/ManagedTextField";
import { Navigate } from "react-router-dom";

export default function login(props) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    if (props.auth.loggedIn) {
        return <Navigate to="/" replace />;
    }

    return (
        <Typography variant="p" component="div">
            Login
            {/* Box for Login and passwored */}
            <Box
                component="div"
                sx={{
                    "& > :not(style)": { mt: 1, width: "25ch" },
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
                        props.auth.loginUser(email, password);
                    }}
                    color="secondary"
                    variant="contained"
                >
                    Submit
                </Button>
            </Box>
            <div>
                <Link to={"/forgot"}>Forgot Password?</Link>
            </div>
        </Typography>
    );
}
