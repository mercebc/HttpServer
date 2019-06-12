package Handlers;

import java.io.IOException;
import java.util.Map;

import Request.Request;
import Response.Response;
import Util.PublicFileManager;

public class IndexHandler extends ResponseHandler {
    PublicFileManager publicFileManager;

    public IndexHandler(PublicFileManager publicFileManager) {
        this.publicFileManager = publicFileManager;
    }

    public Response get(Request request) throws IOException {
        String filename = "Index.html";
        byte[] htmlBody = publicFileManager.read(filename);

        Map<String, String> headers = allowedMethods();
        headers.put("Content-Type", publicFileManager.MIMEType(filename));

        return Response.ok().withHeaders(headers).withBody(htmlBody);

    }
}
