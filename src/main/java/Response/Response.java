package Response;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private Map<String, String> headers = new HashMap<>();
    private byte[] body = new byte[0];
    private String statusLine;

    public Response(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return body;
    }

    public static Response status(int code) {
        return new Response(StatusLineBuilder.create(code));
    }

    public Response withHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public Response withBody(byte[] body){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length", String.valueOf(body.length));
        this.body = body;
        return this.withHeaders(headers);
    }

    public static Response ok() {
        return Response.status(200);
    }

    public static Response redirectTo(String destination) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", destination);
        return Response.status(301).withHeaders(headers);
    }

    public static Response methodNotAllowed() {
        return Response.status(405);
    }

    public static Response notFound() {
        return Response.status(404);
    }
}
