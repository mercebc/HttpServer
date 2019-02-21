package Handlers;

import java.util.HashMap;

public class Response {
    private HashMap<String, String> headers;
    private String body;
    private String statusLine;

    public Response(String statusLine){
        this.statusLine = statusLine;
        this.body = ""; //empty body
    }

    public Response(String statusLine, String body) {
        this.statusLine = statusLine;
        this.body = body;
    }

    public Response(String statusLine, HashMap<String, String> headers, String body){
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return body;
    }
}
