import Responses.OldResponse;

import java.io.IOException;

public class ServerCommunicator {
    private SocketCommunicator socketCommunicator;
    private String requestString;
    private ResponseConverter responseConverter;
    private RequestConverter requestConverter;
    private Router router;

    public ServerCommunicator(SocketCommunicator socketCommunicator) {
        this.socketCommunicator = socketCommunicator;
        this.responseConverter = new ResponseConverter();
        this.requestConverter = new RequestConverter();
        this.router = new Router();
    }

    public String getRequestString() {
        return requestString;
    }

    public void handleRequest() throws IOException {
        Request request = getRequest();
        Response response = router.route(request);
        sendResponse(response);

        socketCommunicator.closeConnection();
    }

    private Request getRequest() throws IOException {
        requestString = socketCommunicator.readFromSocket();
        return requestConverter.stringToRequest(requestString);
    }

    private void sendResponse(Response response) throws IOException {
        String responseString = responseConverter.responseToString(response);
        socketCommunicator.printToSocket(responseString);
    }

}