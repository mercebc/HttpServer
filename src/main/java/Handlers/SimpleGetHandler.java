package Handlers;

public class SimpleGetHandler implements ResponseHandler {
    @Override
    public Response respondToRequest() {
        return new Response("HTTP/1.1 200 OK", "");
    }
}
