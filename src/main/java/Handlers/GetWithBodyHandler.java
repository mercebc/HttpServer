package Handlers;

import Request.Request;
import Response.Response;

public class GetWithBodyHandler extends ResponseHandler {
    public Response head(Request request) { return Response.ok().withHeaders(allowedMethods()); }
    public Response options(Request request) { return Response.ok().withHeaders(allowedMethods()); }
}
