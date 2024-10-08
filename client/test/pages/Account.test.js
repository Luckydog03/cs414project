import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { MemoryRouter } from "react-router-dom";
import Account from "../../src/pages/Account";

describe("Account", () => {
    test("Luckydog: renders Account text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Account
                    showMessage={() => {
                        return "";
                    }}
                    auth={() => {
                        return { email: "Test" };
                    }}
                />
                )
            </MemoryRouter>,
        );

        await screen.findByText(/Account Page/i);
    });
});
