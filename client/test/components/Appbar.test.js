import React from "react";
import { render, screen } from "@testing-library/react";
import { beforeEach, describe, test } from "@jest/globals";
import Appbar from "../../src/components/Appbar";
import { MemoryRouter } from "react-router-dom";

describe("Appbar", () => {
    beforeEach(() => {
        fetch.resetMocks();
    });
    test("luckydog: renders appbar", async () => {
        render(
            <MemoryRouter>
                <Appbar
                    auth={() => {
                        return { loggedIn: false };
                    }}
                />
                )
            </MemoryRouter>,
        );
        await screen.findAllByText(/Welcome to t57!/i);
    });
});
