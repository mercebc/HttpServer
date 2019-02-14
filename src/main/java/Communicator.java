import java.io.IOException;

public class Communicator {
    private StreamSocket sc;
    private String requestString;
    private Translator translate;
    private Generator generator;

    public Communicator(StreamSocket sc) {
        this.sc = sc;
        this.translate = new Translator();
        this.generator = new Generator();
    }

    public String getRequestString() {
        return requestString;
    }

    public void run() throws IOException {
        Request request = getRequest();
        Response response = generator.generateResponse(request);
        sendResponse(response);

        sc.closeConnection();
    }

    private String receive() throws IOException {
        return sc.readFromSocket();
    }

    private Request getRequest() throws IOException {
        requestString = receive();
        return translate.stringToRequest(requestString);
    }

    private void sendResponse(Response response){
        String responseString = translate.responseToString(response);
        sc.printToSocket(responseString);
    }

}