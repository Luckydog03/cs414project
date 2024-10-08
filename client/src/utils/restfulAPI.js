import Ajv from "ajv";
import * as configSchema from "../../schemas/ConfigResponse";
import * as registerSchema from "../../schemas/RegisterRequest.json";
import * as loginSchema from "../../schemas/LoginRequest.json";
import * as getInvitesSchema from "../../schemas/ReadInviteRequest.json";
import * as getUserListSchema from "../../schemas/ReadUserResponse.json";
import * as acceptInviteSchema from "../../schemas/AcceptInviteRequest.json";
import * as rejectInviteSchema from "../../schemas/RejectInviteRequest.json";
import * as sendInviteSchema from "../../schemas/SendInviteRequest.json";
import { LOG } from "./constants";

const SCHEMAS = {
    config: configSchema,
    register: registerSchema,
    login: loginSchema,
    readinvite: getInvitesSchema,
    sendinvite: sendInviteSchema,
    acceptinvite: acceptInviteSchema,
    rejectinvite: rejectInviteSchema,
    readuser: getUserListSchema,
};

export async function sendAPIRequest(requestBody, serverUrl) {
    if (isRequestNotSupported(requestBody)) {
        throw new Error(
            `sendAPIRequest() does not have support for type: ${requestBody.requestType}. Please add the schema to 'SCHEMAS'.`,
        );
    }

    const response = await sendRequest(requestBody, serverUrl);

    if (isJsonResponseValid(response, SCHEMAS[requestBody.requestType])) {
        return response;
    }
    LOG.error(
        `Server ${requestBody.requestType} response json is invalid. Check the Server.`,
    );
    return null;
}

export function isRequestNotSupported(requestBody) {
    return !Object.keys(SCHEMAS).includes(requestBody.requestType);
}

async function sendRequest(requestBody, serverUrl) {
    const fetchOptions = {
        method: "POST",
        body: JSON.stringify(requestBody),
    };

    try {
        const response = await fetch(
            `${serverUrl}/api/${requestBody.requestType}`,
            fetchOptions,
        );
        if (response.ok) {
            return response.json();
        } else {
            LOG.error(
                `Request to server ${serverUrl} failed: ${response.status}: ${response.statusText}`,
            );
        }
    } catch (err) {
        LOG.error(`Request to server failed : ${err}`);
    }

    return null;
}

export function getOriginalServerUrl() {
    const serverProtocol = location.protocol;
    const serverHost = location.hostname;
    const serverPort = location.port;
    const alternatePort = process.env.SERVER_PORT;
    return `${serverProtocol}\/\/${serverHost}:${
        !alternatePort ? serverPort : alternatePort
    }`;
}

export function isJsonResponseValid(object, schema) {
    if (object && schema) {
        const anotherJsonValidator = new Ajv();
        const validate = anotherJsonValidator.compile(schema);
        return validate(object);
    }
    LOG.error(
        `bad arguments - isJsonResponseValid(object: ${object}, schema: ${schema.title})`,
    );
    return false;
}
