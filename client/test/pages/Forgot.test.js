import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import Forgot from "../../src/pages/Forgot";
import { MemoryRouter } from "react-router-dom";

describe("Forgot", () => {
    test("luckydog: renders forgot text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Forgot
                    showMessage={() => {
                        return "";
                    }}
                />
                )
            </MemoryRouter>,
        );

        await screen.findByText(/forgot/i);
    });
});
