import java.io.IOException;

public class Communicator {
    private StreamSocket sc;
    private String requestString;

    public Communicator(StreamSocket sc) {
        this.sc = sc;
    }

    public String getRequestString() {
        return requestString;
    }

    public void run() throws IOException {
        requestString = receive();
        sc.closeConnection();
    }

    private String receive() throws IOException {
        return sc.readFromSocket();
    }

}