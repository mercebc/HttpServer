package Handlers.PublicFileHandlers;

import Request.Request;
import Response.Response;
import Handlers.ResponseHandler;
import Util.PublicFileManager;

import java.io.IOException;
import java.util.Map;

public class PublicFileHandler extends ResponseHandler {
    PublicFileManager publicFileManager;

    public PublicFileHandler(PublicFileManager publicFileManager) {
        this.publicFileManager = publicFileManager;
    }

    public Response get(Request request) throws IOException {
        byte[] body = publicFileManager.read(request.getURI());
        Map<String, String> headers = allowedMethods();
        headers.put("Content-Type", publicFileManager.MIMEType(request.getURI()));

        return Response.ok().withHeaders(headers).withBody(body);

    }
}
