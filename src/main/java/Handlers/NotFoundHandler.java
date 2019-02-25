package Handlers;

public class NotFoundHandler extends ResponseHandler {
    public Response respondToRequest() {
        return new Response("HTTP/1.1 404 Not Found");
    }
}
