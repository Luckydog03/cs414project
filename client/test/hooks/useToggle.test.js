import { describe, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { renderHook } from "@testing-library/react-hooks";
import { useToggle } from "../../src/hooks/useToggle";
import { act } from "react-dom/test-utils";

describe("useUserList", () => {
    test("enzob: inits", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        const { result } = renderHook(() => useToggle(false));

        let [toggled, toggle] = result.current;

        expect(toggled).toBe(false);

        act(() => {
            toggle();
        });

        [toggled, toggle] = result.current;

        expect(toggled).toBe(true);
    });
});
