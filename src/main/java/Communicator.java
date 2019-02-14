import java.io.IOException;

public class Communicator {
    private StreamSocket sc;
    private String requestString;
    private Translator translate;

    public Communicator(StreamSocket sc) {
        this.sc = sc;
        this.translate = new Translator();
    }

    public String getRequestString() {
        return requestString;
    }

    public void run() throws IOException {
        Request request = getRequest();
        sc.closeConnection();
    }

    private String receive() throws IOException {
        return sc.readFromSocket();
    }

    private Request getRequest() throws IOException {
        requestString = receive();
        return translate.StringToRequest(requestString);
    }

}