export default function sendReqWithErrorHandling(
    promise,
    successKey,
    context,
    setter,
) {
    const { showMessage } = context;

    promise
        .then((response) => {
            if (response[successKey]) {
                if (setter) {
                    setter(response[successKey]);
                    return;
                }

                const { updateStateToLoggedIn } = context;
                if (updateStateToLoggedIn) {
                    updateStateToLoggedIn(
                        response["email"],
                        response["user_id"],
                    );
                }
            } else {
                showMessage(
                    `${response["requestType"]} Request Failed`,
                    "error",
                );
            }
        })
        .catch((error) => {
            showMessage(error, "error");
        });
}
