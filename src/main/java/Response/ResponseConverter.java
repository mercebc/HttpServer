package Response;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class ResponseConverter {
    private static final byte[] CRLF = "\r\n".getBytes();
    private static final byte[] EMPTY = new byte[0];

   public static byte[] responseToBytes(Response response) {
       byte[] statusLine = response.getStatusLine().trim().getBytes();
       byte[] headers = EMPTY;
       byte[] messageBody = EMPTY;

       if(hasHeaders(response)) {headers = headersIntoBytes(response.getHeaders());}
       if(hasBody(response)) {messageBody = response.getBody();}


       byte[] allByteArray = new byte[statusLine.length + CRLF.length + headers.length
                + CRLF.length + messageBody.length];

       ByteBuffer buff = ByteBuffer.wrap(allByteArray);
       buff.put(statusLine);
       buff.put(CRLF);
       buff.put(headers);
       buff.put(CRLF);
       buff.put(messageBody);

       return buff.array();
   }

   private static boolean hasHeaders(Response response){
       return !response.getHeaders().isEmpty();
   }

   private static boolean hasBody(Response response){
       return response.getBody().length != 0;
   }

   public static byte[] headersIntoBytes(Map<String, String> headers){
       String crlf = "\r\n";
       String result = "";

       Iterator it = headers.entrySet().iterator();
       while (it.hasNext()) {
           Map.Entry resource = (Map.Entry) it.next();
           result += resource.getKey() + ": " + resource.getValue() + crlf;
           it.remove();
       }
       return result.getBytes();
   }

}
