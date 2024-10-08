import { createBrowserRouter, RouterProvider } from "react-router-dom";
import React from "react";
import Home from "../pages/Home";
import Page from "./Page";
import Login from "../pages/Login";
import Forgot from "../pages/Forgot";
import Game from "../pages/Game";
import Register from "../pages/Register";
import Account from "../pages/Account";
import About from "../pages/About";
import Invite from "../pages/Invite";
import RequireAuth from "../components/RequireAuth";

export default function Router(props) {
    const router = createBrowserRouter([
        {
            path: "/",
            element: <Page {...props} />,
            children: [
                {
                    path: "/",
                    element: <Home {...props} />,
                },
                {
                    path: "/register",
                    element: <Register {...props} />,
                },
                {
                    path: "/login",
                    element: <Login {...props} />,
                },
                {
                    path: "/forgot",
                    element: <Forgot {...props} />,
                },
                {
                    path: "/game",
                    element: (
                        <RequireAuth {...props}>
                            <Game {...props} />
                        </RequireAuth>
                    ),
                },
                {
                    path: "/account",
                    element: (
                        <RequireAuth {...props}>
                            <Account {...props} />
                        </RequireAuth>
                    ),
                },
                {
                    path: "/about",
                    element: <About {...props} />,
                },
                {
                    path: "/invite",
                    element: (
                        <RequireAuth {...props}>
                            <Invite {...props} />
                        </RequireAuth>
                    ),
                },
            ],
        },
    ]);

    return <RouterProvider router={router} />;
}
