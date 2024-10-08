import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { MemoryRouter } from "react-router-dom";
import Invite from "../../src/pages/Invite";

describe("Invite", () => {
    test("Luckydog: renders invite text", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(
            <MemoryRouter>
                <Invite
                    showMessage={() => {
                        return "";
                    }}
                    auth={{
                        invites: { invites: [], getInvites: () => {} },
                        userList: [],
                        getUserList: () => {},
                    }}
                />
                )
            </MemoryRouter>,
        );

        await screen.findByText(/Invites/i);
    });
});
