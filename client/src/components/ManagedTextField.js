import { TextField } from "@mui/material";
import React from "react";

export default function ManagedTextField(props) {
    return (
        <TextField //Display Name
            value={props.value[0]}
            onChange={(e) => props.value[1](e.target.value)}
            sx={{ display: "block" }}
            label={props.label}
            type={props.password ? "password" : ""}
            variant="filled"
        />
    );
}
