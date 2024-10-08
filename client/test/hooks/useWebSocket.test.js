import { describe, expect, test } from "@jest/globals";
import { LOG } from "../../src/utils/constants";
import useWebSocket from "../../src/hooks/useWebSocket";

describe("useWebSocket", () => {
    test("luckydog: inits", async () => {
        jest.spyOn(LOG, "error").mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        const socket = new useWebSocket("ws://localhost:1234", () => {});
        
        socket.connect();

    });
});