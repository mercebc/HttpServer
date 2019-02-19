public class OKResponseHandler implements Handler {
    @Override
    public Response respondToRequest() {
        return new Response("HTTP/1.1 200 OK", "");
    }
}
