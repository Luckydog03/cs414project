import { describe, expect, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import { useServerInputValidation } from "../../src/hooks/useServerInputValidation";
import { renderHook } from "@testing-library/react-hooks";
import { act } from "@testing-library/react-hooks";

describe("useServerInputValidation", () => {
    test("luckydog: inits", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        const { result } = renderHook(() =>
            useServerInputValidation("", () => {}),
        );

        const [serverInput, updateServerInput, config, validServer, resetModal] = result.current;
        // console.log(auth);

        expect(typeof updateServerInput).toBe("function");
        expect(typeof resetModal).toBe("function");
        expect(serverInput).toBe("");
        expect(validServer).toBe(false);
        expect(config).toBe(null);

        updateServerInput("");
        act(() => {
            resetModal("", () => {});
        });

    });
});