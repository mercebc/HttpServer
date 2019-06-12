package Handlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;

public class EchoBodyHandler extends ResponseHandler {

    public Response post(Request request) {
        byte[] echoBody = request.getMessageBody().trim().getBytes();
        addHeader("Content-Length", String.valueOf(echoBody.length));
        return new Response(StatusLineBuilder.create(200), getHeaders(), echoBody);
    }


}
