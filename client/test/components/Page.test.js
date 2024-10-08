import React from "react";
import { render, screen } from "@testing-library/react";
import { beforeEach, describe, test } from "@jest/globals";
import Page from "../../src/components/Page";
import { MemoryRouter } from "react-router-dom";
import { LOG } from "../../src/utils/constants";

describe("Page", () => {
    beforeEach(() => {
        fetch.resetMocks();
    });
    test("enzob: page base renders header text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Page
                    showMessage={() => {
                        return "";
                    }}
                    serverSettings={() => {
                        return { serverUrl: "" };
                    }}
                    auth={() => {
                        return { loggedIn: false };
                    }}
                />
            </MemoryRouter>,
        );

        await screen.findByText(/Dungeons and Debuggers/i);
    });
});
