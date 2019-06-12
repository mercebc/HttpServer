package Response;

import java.util.HashMap;

public class Response {
    private HashMap<String, String> headers = new HashMap<>();
    private byte[] body = new byte[0];
    private String statusLine;

    public Response(String statusLine, byte[] body) {
        this.statusLine = statusLine;
        this.body = body;
    }

    public Response(String statusLine, HashMap<String, String> headers){
        this.statusLine = statusLine;
        this.headers = headers;
    }

    public Response(String statusLine, HashMap<String, String> headers, byte[] body){
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }

    public Response(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return body;
    }
}
