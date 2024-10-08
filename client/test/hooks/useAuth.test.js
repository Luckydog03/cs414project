import { describe, expect, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import useAuth from "../../src/hooks/useAuth";
import { renderHook } from "@testing-library/react-hooks";

describe("useAuth", () => {
    test("luckydog: inits", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        const { result } = renderHook(() =>
            useAuth("", "", () => {}),
        );

        const [auth] = result.current
        
        expect(auth.loggedIn).toBe(false);
        expect(typeof auth.registerUser).toBe("function");
        expect(typeof auth.loginUser).toBe("function");

        auth.registerUser("", "");
        auth.loginUser("", "");
        auth.logout();
    });
});