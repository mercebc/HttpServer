package Request;

import java.util.HashMap;

public class RequestConverter {
    private static final String SPACE = "\\s+";
    private static final String CRLF = "\r\n";
    private static final String COLON = ": ";

    public static Request stringToRequest(String request) {
        String[] requestBlocs = request.split(CRLF);

        String requestLine = requestBlocs[0];
        HashMap<String, String> headers = convertHeaders(requestBlocs);
        String body = convertBody(requestBlocs);

        return createRequest(requestLine.split(SPACE), headers, body);
    }

    private static HashMap<String, String> convertHeaders(String[] requestBlocs) {
        HashMap<String, String> headers = new HashMap<>();

        for (int i = 1; i < requestBlocs.length; i++) {
            if (!requestBlocs[i].isEmpty()) {
                String[] header = requestBlocs[i].split(COLON);
                headers.put(header[0].trim(), header[1].trim());
            } else{
                return headers;
            }
        }
        return headers;
    }


    private static String convertBody(String[] requestBlocs){
        String body = "";
        for (int i = 1; i < requestBlocs.length; i++) {
            if(requestBlocs[i].isEmpty()){
                body = requestBlocs[i + 1];
            }
        }
        return body;
    }


    private static Request createRequest(String[] requestLine, HashMap<String, String> headers, String body) {
        try {
            return new Request(requestLine[0], requestLine[1], requestLine[2], headers, body);
        } catch (Exception ArrayIndexOutOfBoundsException) {
            return new Request("","","");
        }

    }
}
