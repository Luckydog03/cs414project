import React from "react";
import { Link } from "react-router-dom";
import { Box, Button, Typography, Menu, MenuItem } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Avatar from "@mui/material/Avatar";
import EmailIcon from "@mui/icons-material/Email";
import "./styles/Appbar.css";

export default function Appbar(props) {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        id="basic-button"
                        aria-controls={open ? "menu-burger" : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? "true" : undefined}
                        onClick={handleClick}
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="basic-button"
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Menu
                        id="menu-burger"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{
                            "aria-labelledby": "basic-button",
                        }}
                    >
                        <Typography>
                            {props.auth.loggedIn ? (
                                <Link to={"/account"}>
                                    <MenuItem onClick={handleClose}>
                                        My Account
                                    </MenuItem>
                                </Link>
                            ) : (
                                <Link to={"/login"}>
                                    <MenuItem onClick={handleClose}>
                                        Login
                                    </MenuItem>
                                </Link>
                            )}
                        </Typography>
                        {props.auth.loggedIn ? (
                            <MenuItem onClick={handleClose}>
                                <Link to={"/invite"}>Invite/Requests</Link>
                            </MenuItem>
                        ) : (
                            <div></div>
                        )}
                        <Link to={"/about"}>
                            <MenuItem onClick={handleClose}>About Us</MenuItem>
                        </Link>
                        {props.auth.loggedIn ? (
                            <MenuItem
                                onClick={() => {
                                    props.auth.logout();
                                    handleClose();
                                }}
                            >
                                Logout
                            </MenuItem>
                        ) : (
                            <div></div>
                        )}
                    </Menu>

                    <Typography
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1 }}
                    >
                        <Link to={"/"} style={{ textDecoration: "none" }}>
                            Welcome to t57!
                        </Link>
                    </Typography>
                    {!props.auth.loggedIn ? (
                        <Link to={"/login"}>
                            <Button color="inherit">Login</Button>
                        </Link>
                    ) : (
                        <Box
                            sx={{
                                display: "flex",
                                flexDirection: "row",
                                alignItems: "center",
                            }}
                        >
                            <Button>
                                <Link to={"/invite"}>
                                    <EmailIcon
                                        sx={{ color: "#FFFF" }}
                                    ></EmailIcon>
                                </Link>
                            </Button>
                            <Link to={"/account"}>
                                <Avatar
                                    sx={{ bgcolor: "#D9782D", ml: 0 }}
                                    src="/broken-image.jpg"
                                >
                                    {props.auth.email.charAt(0)}
                                </Avatar>
                            </Link>
                        </Box>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
}
