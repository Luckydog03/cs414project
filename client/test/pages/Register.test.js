import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { MemoryRouter } from "react-router-dom";
import Register from "../../src/pages/Register";

describe("Register", () => {
    test("StumpyLabs: renders register text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Register
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

        await screen.findByText(/register/i);
    });
});
