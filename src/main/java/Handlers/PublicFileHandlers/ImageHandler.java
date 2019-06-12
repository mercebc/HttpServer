package Handlers.PublicFileHandlers;

import Core.Request;
import Core.Response;
import Core.StatusLineBuilder;
import Handlers.ResponseHandler;
import Util.PublicFileReader;

import java.io.IOException;

public class ImageHandler extends ResponseHandler {
    PublicFileReader publicFileReader;

    public ImageHandler(PublicFileReader publicFileReader) {
        this.publicFileReader = publicFileReader;
    }

    public Response get(Request request) throws IOException {
        String imageBody = publicFileReader.readImage("/images/wellies.png");
        System.out.println(imageBody);
        addHeader("Content-Type", publicFileReader.MIMEType("png"));
        addHeader("Content-Length", String.valueOf(imageBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), imageBody);
    }
}
