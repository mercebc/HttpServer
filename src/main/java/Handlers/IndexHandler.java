package Handlers;

public class IndexHandler extends ResponseHandler {

    public Response get(Request request) {
        String htmlBody = "<html><body><p>Find out if you are going to need wellies in your town!</p></body></html>";
        addHeader("Content-Length", String.valueOf(htmlBody.length()));
        return new Response(StatusLineBuilder.create(200), getHeaders(), htmlBody);

    }
}
