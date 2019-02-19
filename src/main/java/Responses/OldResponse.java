package Responses;

public interface OldResponse {
    String protocol = "HTTP/1.1";

    String getStatusLine();

    String getBody();

    int getCode();

}
