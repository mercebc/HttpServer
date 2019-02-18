import java.io.IOException;

public class ServerCommunicator {
    private SocketCommunicator socketCommunicator;
    private String requestString;
    private ResponseConverter responseConverter;
    private RequestConverter requestConverter;
    private Generator generator;

    public ServerCommunicator(SocketCommunicator socketCommunicator) {
        this.socketCommunicator = socketCommunicator;
        this.responseConverter = new ResponseConverter();
        this.requestConverter = new RequestConverter();
        this.generator = new Generator();
    }

    public String getRequestString() {
        return requestString;
    }

    public void run() throws IOException {
        Request request = getRequest();
        Response response = generator.generateResponse(request);
        sendResponse(response);

        socketCommunicator.closeConnection();
    }

    private String receive() throws IOException {
        return socketCommunicator.readFromSocket();
    }

    private Request getRequest() throws IOException {
        requestString = receive();
        System.out.println(requestString);
        return requestConverter.stringToRequest(requestString);
    }

    private void sendResponse(Response response) throws IOException {
        String responseString = responseConverter.responseToString(response);
        socketCommunicator.printToSocket(responseString);
    }

}