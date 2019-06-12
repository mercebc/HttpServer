package Handlers;

import Request.Request;
import Response.Response;

public class MethodOptionsHandler extends ResponseHandler {
    public Response get(Request request) { return Response.ok().withHeaders(allowedMethods()); }
    public Response head(Request request) { return Response.ok().withHeaders(allowedMethods()); }
    public Response options(Request request) { return Response.ok().withHeaders(allowedMethods()); }
}
