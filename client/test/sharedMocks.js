export const VALID_CONFIG_RESPONSE = JSON.stringify({
    requestType: "config",
    serverName: "t57",
    features: ["config"],
});

export const INVALID_REQUEST = JSON.stringify({
    invalid: "this is an invalid response to fail the schema",
});
