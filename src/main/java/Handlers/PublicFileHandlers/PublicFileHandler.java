package Handlers.PublicFileHandlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;
import Handlers.ResponseHandler;
import Util.PublicFileManager;

import java.io.IOException;

public class PublicFileHandler extends ResponseHandler {
    PublicFileManager publicFileManager;

    public PublicFileHandler(PublicFileManager publicFileManager) {
        this.publicFileManager = publicFileManager;
    }

    public Response get(Request request) throws IOException {
        byte[] body = publicFileManager.read(request.getURI());
        addHeader("Content-Type", publicFileManager.MIMEType(request.getURI()));
        addHeader("Content-Length", String.valueOf(body.length));
        return new Response(StatusLineBuilder.create(200), getHeaders(), body);
    }
}
