package Responses;

public class NotFoundOldResponse implements OldResponse {
    private int code;
    private String status;
    private String body;

    public NotFoundOldResponse(){
        this.code = 404;
        this.status = "Not Found";
        this.body = "";
    }

    public String getBody() {
        return body;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getStatusLine(){
        return protocol + " " + this.code + " " + this.status;
    }
}
