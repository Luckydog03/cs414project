import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { MemoryRouter } from "react-router-dom";
import Home from "../../src/pages/Home";

describe("Home", () => {
    test("enzob: renders home text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Home
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

        await screen.findByText(/Chess Homepage/i);
    });
});
