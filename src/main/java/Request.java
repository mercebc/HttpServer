import java.util.HashMap;

public class Request {

    private String[] requestLine;
    private HashMap<String, String> headers;
    private String messageBody;


    public Request(String verb, String URI, String protocol, HashMap<String, String> headers, String messageBody) {

        this.requestLine = new String[3];

        this.requestLine[0] = verb;
        this.requestLine[1] = URI;
        this.requestLine[2] = protocol;

        this.headers = headers;

        this.messageBody = messageBody;

    }

    public String[] getRequestLine() {
        return requestLine;
    }

    public String getMethod(){ return requestLine[0]; }

    public String getURI() {
        return requestLine[1];
    }

    public String getProtocol() {
        return requestLine[2];
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
