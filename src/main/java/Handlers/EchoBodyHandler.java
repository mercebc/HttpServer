package Handlers;

public class EchoBodyHandler extends ResponseHandler {


    public Response post(Request request) {
        String echoBody = request.getMessageBody().trim();
        addHeader("Content-Length", String.valueOf(echoBody.length()));

        return new Response("HTTP/1.1 200 OK", getHeaders(), echoBody);
    }


}
