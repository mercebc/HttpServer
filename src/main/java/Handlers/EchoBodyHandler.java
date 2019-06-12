package Handlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;

public class EchoBodyHandler extends ResponseHandler {

    public Response post(Request request) {
        String echoBody = request.getMessageBody().trim();
        addHeader("Content-Length", String.valueOf(echoBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), echoBody);
    }


}
