import Handlers.Response;

import java.util.HashMap;
import java.util.Iterator;

public class ResponseConverter {
    private static final String NEWLINE = "\n";
    private static final String EMPTY = "";

   public String responseToString(Response response) {

       String statusLine = response.getStatusLine().trim();
       String headers = EMPTY;
       String messageBody = EMPTY;

       if(hasHeaders(response)) {headers = NEWLINE + headersIntoString(response.getHeaders());}
       if(hasBody(response)) {messageBody = NEWLINE + NEWLINE + response.getBody();}

       return statusLine + headers + messageBody;

   }

   private boolean hasHeaders(Response response){
       return !response.getHeaders().isEmpty();
   }

   private boolean hasBody(Response response){
       return !response.getBody().isEmpty();
   }

   public String headersIntoString(HashMap<String, String> headers){
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
