package Handlers.PublicFileHandlers;

import Core.Request;
import Core.Response;
import Core.StatusLineBuilder;
import Handlers.ResponseHandler;
import Util.PublicFileReader;

import java.io.IOException;

public class CssHandler extends ResponseHandler {
    PublicFileReader publicFileReader;

    public CssHandler(PublicFileReader publicFileReader) {
        this.publicFileReader = publicFileReader;
    }

    public Response get(Request request) throws IOException {
        String htmlBody = publicFileReader.read("css/app-stylesheet.css");
        addHeader("Content-Type", publicFileReader.MIMEType("css"));
        addHeader("Content-Length", String.valueOf(htmlBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
