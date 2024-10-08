export default class useWebSocket {
    constructor(websocketUrl, showMessage) {
        this.websocketUrl = websocketUrl;
        this.showMessage = showMessage;
    }

    connect() {
        this.socket = new WebSocket(this.websocketUrl);

        return new Promise((resolve, reject) => {
            this.socket.onopen = () => {
                resolve(this.socket);
            };
            this.socket.onerror = (err) => {
                this.showMessage(err, "error");
                reject(err);
            };
        });
    }

    disconnect() {
        this.socket.close();
    }

    sendMsg(msg) {
        this.socket.send(msg);
    }
}
