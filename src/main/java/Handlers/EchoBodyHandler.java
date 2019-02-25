package Handlers;

public class EchoBodyHandler extends ResponseHandler {

    public Response post(Request request) {
        return new Response("HTTP/1.1 200 OK", request.getMessageBody().trim());
    }
}
