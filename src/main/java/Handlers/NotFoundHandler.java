public class NotFoundHandler implements ResponseHandler {
    @Override
    public Response respondToRequest() {
        return new Response("HTTP/1.1 404 Not Found");
    }
}
