package Handlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;

public class MethodOptionsHandler extends ResponseHandler {
    public Response get(Request request) { return new Response(StatusLineBuilder.create(200), getHeaders()); }
    public Response head(Request request) { return new Response(StatusLineBuilder.create(200), getHeaders()); }
    public Response options(Request request) { return new Response(StatusLineBuilder.create(200), getHeaders()); }
}
