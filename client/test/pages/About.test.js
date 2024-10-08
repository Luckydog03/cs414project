import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { MemoryRouter } from "react-router-dom";
import About from "../../src/pages/About";

describe("About", () => {
    test("Luckydog: renders about text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <About
                    showMessage={() => {
                        return "";
                    }}
                />
                )
            </MemoryRouter>,
        );

        await screen.findByText(/mission/i);
    });
});
