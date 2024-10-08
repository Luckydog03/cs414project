import { describe, expect, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import useInvite from "../../src/hooks/useInvite";
import { renderHook } from "@testing-library/react-hooks";

describe("useInvite", () => {
    test("luckydog: inits", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        const { result } = renderHook(() =>
            useInvite("", "", () => {},() => {}),
        );

        const auth = result.current;

        expect(typeof auth.getInvites).toBe("function");
        expect(auth.invites.length).toBe(0);

        auth.getInvites();
        auth.sendInvite("anthony");
        auth.acceptInvite("123");
        auth.rejectInvite("123");
    });
});