package Handlers.PublicFileHandlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;
import Handlers.ResponseHandler;
import Util.PublicFileManager;

import java.io.IOException;

public class ImageHandler extends ResponseHandler {
    PublicFileManager publicFileManager;

    public ImageHandler(PublicFileManager publicFileManager) {
        this.publicFileManager = publicFileManager;
    }

    public Response get(Request request) throws IOException {
        String imageBody = publicFileManager.read("/images/wellies.png");
        addHeader("Content-Type", publicFileManager.MIMEType("png"));
        addHeader("Content-Length", String.valueOf(imageBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), imageBody);
    }
}
