import java.util.HashMap;

public class Request {

    private String[] request_line;
    private HashMap<String, String> headers;
    private String message_body;


    public Request(String verb, String URI, String protocol) {

        this.request_line = new String[3];

        this.request_line[0] = verb;
        this.request_line[1] = URI;
        this.request_line[2] = protocol;

    }

    public String getVerb() {
        return request_line[0];
    }

    public String getURI() {
        return request_line[1];
    }

    public String getProtocol() {
        return request_line[2];
    }
}
