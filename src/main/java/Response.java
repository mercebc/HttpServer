public class Response {
    private String headers;
    private String body;

    public Response(String headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    public String getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return body;
    }
}
