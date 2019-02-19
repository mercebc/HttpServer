package Responses;

public class NotAllowedOldResponse implements OldResponse {
    private int code;
    private String status;
    private String body;

    public NotAllowedOldResponse(){
        this.code = 405;
        this.status = "Not Allowed";
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
