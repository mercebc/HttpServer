package Handlers;

public class SimpleGetHandler extends ResponseHandler {

    public Response get(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }

    public Response head(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }

}
