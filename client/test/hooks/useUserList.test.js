import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import useUserList from "../../src/hooks/useUserList";
import { renderHook } from "@testing-library/react-hooks";

describe("useUserList", () => {
    test("enzob: inits", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        const { result } = renderHook(() =>
            useUserList("example.com", "enzo", () => {}),
        );

        const [userList, getUserList] = result.current;

        expect(typeof getUserList).toBe("function");
        expect(userList.length).toBe(0);

        getUserList();
    });
});
