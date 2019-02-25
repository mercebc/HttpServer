import java.util.HashMap;

public class RequestConverter {
    private static final String SPACE = "\\s+";
    private static final String NEWLINE = "\r\n";
    private static final String COLON = ": ";

    public Request stringToRequest(String request) {
        String[] requestBlocs = request.split(NEWLINE);

        String requestLine = requestBlocs[0];
        HashMap<String, String> headers = convertHeaders(requestBlocs);
        String body = convertBody(requestBlocs);

        return createRequest(requestLine.split(SPACE), headers, body);
    }

    private HashMap<String, String> convertHeaders(String[] requestBlocs) {
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


    private String convertBody(String[] requestBlocs){
        String body = "";
        for (int i = 1; i < requestBlocs.length; i++) {
            if(requestBlocs[i].isEmpty()){
                body = requestBlocs[i + 1];
            }
        }
        return body;
    }


    private Request createRequest(String[] requestLine, HashMap<String, String> headers, String body) {
        return new Request(requestLine[0], requestLine[1], requestLine[2], headers, body);
    }
}
