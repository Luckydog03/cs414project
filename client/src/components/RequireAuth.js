import React from "react";

export default function RequireAuth(props) {
    return props.auth.loggedIn ? props.children : <></>;
}
