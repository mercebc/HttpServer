package Handlers.PublicFileHandlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;
import Handlers.ResponseHandler;
import Util.PublicFileManager;

import java.io.IOException;

public class CssHandler extends ResponseHandler {
    PublicFileManager publicFileManager;

    public CssHandler(PublicFileManager publicFileManager) {
        this.publicFileManager = publicFileManager;
    }

    public Response get(Request request) throws IOException {
        String htmlBody = publicFileManager.read("css/app-stylesheet.css");
        addHeader("Content-Type", publicFileManager.MIMEType("css"));
        addHeader("Content-Length", String.valueOf(htmlBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
