public class Response {
    private String protocol = "HTTP/1.1";
    private int code;
    private String status;
    private String body;

    public Response(){}

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public String getStatus() {
        return status;
    }
}
