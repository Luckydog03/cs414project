import React from "react";
import { Link } from "react-router-dom";
import { Button } from "@mui/material";
import CatGif from "../static/images/cat_chess.gif";

export default function Home(props) {
    return (
        <div>
            <p>Chess Homepage</p>
            {!props.auth.loggedIn ? (
                <Link to={"/login"}>
                    <Button
                        sx={{ mr: 1 }}
                        color="secondary"
                        variant="contained"
                    >
                        Log In
                    </Button>
                </Link>
            ) : (
                <></>
            )}
            {!props.auth.loggedIn ? (
                <Link to={"/register"}>
                    <Button color="secondary" variant="contained">
                        Register
                    </Button>
                </Link>
            ) : (
                <></>
            )}
            {props.auth.loggedIn ? (
                <Link to={"/game"}>
                    <img src={CatGif} />
                    <br />
                    <Button
                        sx={{ mt: 1 }}
                        color="secondary"
                        variant="contained"
                    >
                        Play!
                    </Button>
                </Link>
            ) : (
                <></>
            )}
        </div>
    );
}
