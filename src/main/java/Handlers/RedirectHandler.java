package Handlers;

import java.util.HashMap;

public class RedirectHandler implements ResponseHandler {

    private HashMap<String, String> headers = new HashMap<>();


    public void addHeader(String name, String value){
        this.headers.put(name, value);
    }

    @Override
    public Response respondToRequest() {
        addHeader("Location", "http://0.0.0.0:5000/simple_get");
        return new Response("HTTP/1.1 301 Moved Permanently", this.headers, "");
    }
}
