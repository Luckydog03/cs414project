import React from "react";
import { SnackbarProvider, useSnackbar } from "notistack";
import Router from "./Router";
import { ThemeProvider } from "@mui/material";
import { theme } from "../utils/theme";
import { useServerSettings } from "../hooks/useServerSettings";
import useAuth from "../hooks/useAuth";

export default function App() {
    return (
        <SnackbarProvider maxSnack={3} preventDuplicate>
            <ThemeProvider theme={theme}>
                <HookCaller />
            </ThemeProvider>
        </SnackbarProvider>
    );
}

export const HookCaller = () => {
    const { enqueueSnackbar } = useSnackbar();

    function showMessage(message, variant = "info") {
        enqueueSnackbar(message, { variant: variant });
    }

    const [serverSettings] = useServerSettings(showMessage);
    const [auth] = useAuth(
        serverSettings.serverUrl,
        "ws://localhost:41557",
        showMessage,
    );

    return (
        <Router
            showMessage={showMessage}
            serverSettings={serverSettings}
            auth={auth}
        />
    );
};
