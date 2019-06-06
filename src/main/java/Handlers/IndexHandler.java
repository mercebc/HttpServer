package Handlers;

import java.io.IOException;

import Core.Request;
import Core.Response;
import Core.StatusLineBuilder;
import Util.FileReaderWrapper;

public class IndexHandler extends ResponseHandler {

    public Response get(Request request) throws IOException {
        String htmlBody = FileReaderWrapper.read("/Users/miss.merce/Workspace/Java/HttpServer/src/test/java/Public/Index.html");
        addHeader("Content-Length", String.valueOf(htmlBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
