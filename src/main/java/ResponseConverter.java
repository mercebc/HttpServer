import Handlers.Response;

import java.util.HashMap;
import java.util.Iterator;

public class ResponseConverter {
    private static final String NEWLINE = "\n";
    private static final String CRLF = "\r\n";
    private static final String EMPTY = "";

   public static String responseToString(Response response) {

       String statusLine = response.getStatusLine().trim();
       String headers = EMPTY;
       String messageBody = EMPTY;

       if(hasHeaders(response)) {headers = headersIntoString(response.getHeaders()) + CRLF;}
       if(hasBody(response)) {messageBody = response.getBody();}

       return statusLine + NEWLINE + headers + CRLF + messageBody;

   }

   private static boolean hasHeaders(Response response){
       return !response.getHeaders().isEmpty();
   }

   private static boolean hasBody(Response response){
       return !response.getBody().isEmpty();
   }

   public static String headersIntoString(HashMap<String, String> headers){
       String result = EMPTY;

       Iterator it = headers.entrySet().iterator();
       while (it.hasNext()) {
           HashMap.Entry resource = (HashMap.Entry) it.next();
           result += resource.getKey() + ": " + resource.getValue() + NEWLINE;
           it.remove();
       }
       return result.trim();
   }

}
