package Handlers;

import Request.Request;
import Response.Response;

public class MethodOptions2Handler extends ResponseHandler {
    public Response get(Request request) { return Response.ok().withHeaders(allowedMethods()); }
    public Response head(Request request) { return Response.ok().withHeaders(allowedMethods()); }
    public Response options(Request request) {
        return Response.ok().withHeaders(allowedMethods());
    }
    public Response put(Request request) { return Response.ok().withHeaders(allowedMethods()); }
    public Response post(Request request) {
        return Response.ok().withHeaders(allowedMethods());
    }
}
