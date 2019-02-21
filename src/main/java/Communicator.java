import java.io.IOException;

public class ServerCommunicator {
    private SocketHelper socketHelper;
    private String requestString;
    private ResponseConverter responseConverter;
    private RequestConverter requestConverter;

    public ServerCommunicator(SocketHelper socketHelper) {
        this.socketHelper = socketHelper;
        this.responseConverter = new ResponseConverter();
        this.requestConverter = new RequestConverter();
    }

    public String getRequestString() {
        return requestString;
    }

    public Request getRequest() throws IOException {
        requestString = socketHelper.readFromSocket();
        return requestConverter.stringToRequest(requestString);
    }

    public void sendResponse(Response response) throws IOException {
        String responseString = responseConverter.responseToString(response);
        socketHelper.printToSocket(responseString);
    }

}