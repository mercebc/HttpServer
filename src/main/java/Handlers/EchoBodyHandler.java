package Handlers;

import Request.Request;
import Response.Response;

public class EchoBodyHandler extends ResponseHandler {

    public Response post(Request request) {
        byte[] echoBody = request.getMessageBody().trim().getBytes();
        return Response.ok().withHeaders(allowedMethods()).withBody(echoBody);
    }


}
