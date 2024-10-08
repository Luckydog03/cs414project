import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { MemoryRouter } from "react-router-dom";
import Game from "../../src/pages/Game";

describe("Game", () => {
    test("StumpyLabs: renders game text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Game
                    showMessage={() => {
                        return "";
                    }}
                    auth={{ matchID: "" }}
                />
                )
            </MemoryRouter>,
        );

        await screen.findByText(/Game/i);
    });
});
