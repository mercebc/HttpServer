package Handlers;

import java.util.HashMap;
import java.util.Map;

public class StatusLineBuilder {
    private static final String SPACE = " ";
    private static final String protocol = "HTTP/1.1";
    private static Map<String, String> status = createStatusMap();

    private static Map<String, String> createStatusMap() {
        Map<String,String> status = new HashMap<>();
        status.put("200", "OK");
        status.put("301", "Moved Permanently");
        status.put("404", "Not Found");
        status.put("405", "Method Not Allowed");
        return status;
    }

    public static String create(int code){
        String statusCode = Integer.toString(code);
        return protocol + SPACE + statusCode + SPACE + status.get(statusCode);
    }

}
