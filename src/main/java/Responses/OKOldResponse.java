package Responses;

public class OKOldResponse implements OldResponse {
    private int code;
    private String status;
    private String body;

    public OKOldResponse(){
        this.code = 200;
        this.status = "OK";
        this.body = "";
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getStatusLine(){
        return protocol + " " + this.code + " " + this.status;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public int getCode() {
        return code;
    }
}
