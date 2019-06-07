package Handlers;

import java.io.IOException;

import Core.Request;
import Core.Response;
import Core.StatusLineBuilder;
import Util.PublicFileReader;

public class IndexHandler extends ResponseHandler {
    PublicFileReader publicFileReader;

    public IndexHandler(PublicFileReader publicFileReader) {
        this.publicFileReader = publicFileReader;
    }

    public Response get(Request request) throws IOException {
        String htmlBody = publicFileReader.read("Index.html");
        addHeader("Content-Type", publicFileReader.MIMEType("Index.html"));
        addHeader("Content-Length", String.valueOf(htmlBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
