package Handlers;

import java.io.IOException;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;
import Util.PublicFileManager;

public class IndexHandler extends ResponseHandler {
    PublicFileManager publicFileManager;

    public IndexHandler(PublicFileManager publicFileManager) {
        this.publicFileManager = publicFileManager;
    }

    public Response get(Request request) throws IOException {
        String htmlBody = publicFileManager.read("Index.html");
        addHeader("Content-Type", publicFileManager.MIMEType("html"));
        addHeader("Content-Length", String.valueOf(htmlBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
