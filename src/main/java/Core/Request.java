package Core;

import java.util.HashMap;

public class Request {

    private String method;
    private String URI;
    private String protocol;
    private HashMap<String, String> headers;
    private String messageBody;


    public Request(String method, String URI, String protocol, HashMap<String, String> headers, String messageBody) {
        this.method = method;
        this.URI = URI;
        this.protocol = protocol;
        this.headers = headers;
        this.messageBody = messageBody;

    }

    public String getMethod(){ return method; }

    public String getURI() { return URI; }

    public String getProtocol() { return protocol; }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
