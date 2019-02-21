import Handlers.Response;

import java.util.HashMap;
import java.util.Iterator;

public class ResponseConverter {

   public String responseToString(Response response) {

       String statusLine = response.getStatusLine();
       String headers = "";
       String messageBody = "";
       if(hasHeaders(response)) {headers = headersIntoString(response.getHeaders());}
       if(hasBody(response)) {messageBody = response.getBody();}

       return statusLine + "\n" + headers + "\n" + messageBody;

   }

   public boolean hasHeaders(Response response){
       return !response.getHeaders().isEmpty();
   }

   public boolean hasBody(Response response){
       return !response.getBody().isEmpty();
   }

    public String headersIntoString(HashMap<String, String> headers){
       String result = "";
       Iterator it = headers.entrySet().iterator();
       while (it.hasNext()) {
           HashMap.Entry pair = (HashMap.Entry) it.next();
           result += pair.getKey() + ": " + pair.getValue() + "\n";
           it.remove(); // avoids a ConcurrentModificationException
       }
       return result;
    }

}
