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
        String filename = "Index.html";
        byte[] htmlBody = publicFileManager.read(filename);
        addHeader("Content-Type", publicFileManager.MIMEType(filename));
        addHeader("Content-Length", String.valueOf(htmlBody.length));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
