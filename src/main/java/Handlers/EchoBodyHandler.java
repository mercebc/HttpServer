package Handlers;

import Core.Request;
import Core.Response;
import Core.StatusLineBuilder;

public class EchoBodyHandler extends ResponseHandler {

    public Response post(Request request) {
        String echoBody = request.getMessageBody().trim();
        addHeader("Content-Length", String.valueOf(echoBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), echoBody);
    }


}
