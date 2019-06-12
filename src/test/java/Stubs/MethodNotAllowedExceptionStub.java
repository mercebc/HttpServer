package Stubs;

import Handlers.SimpleGetHandler;
import Request.Request;
import Response.Response;

public class MethodNotAllowedExceptionStub extends SimpleGetHandler {
    private Response options(Request request) { return Response.status(200);}
}
