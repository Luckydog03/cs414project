import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import Login from "../../src/pages/Login";
import { MemoryRouter } from "react-router-dom";

describe("login", () => {
    test("luckydog: renders login text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Login
                    showMessage={() => {
                        return "";
                    }}
                    auth={() => {
                        return { loggedIn: false };
                    }}
                />
                )
            </MemoryRouter>,
        );

        await screen.findByText(/login/i);
    });
});
