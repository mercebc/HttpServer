package Handlers;


public class RedirectHandler extends ResponseHandler {

    public Response get(Request request) {
        addHeader("Location", "http://0.0.0.0:5000/simple_get");
        return new Response("HTTP/1.1 301 Moved Permanently", getHeaders(), "");
    }
}
