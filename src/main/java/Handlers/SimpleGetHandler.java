public class SimpleGetHandler implements ResponseHandler {
    @Override
    public Response respondToRequest() {
        return new Response("200 OK");
    }
}
