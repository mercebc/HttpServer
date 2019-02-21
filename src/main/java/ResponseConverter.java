import Handlers.Response;

public class ResponseConverter {



    public String responseToString(Response response) {

       String statusLine = response.getStatusLine();
       String messageBody = response.getBody();

       return statusLine + "\n" + messageBody;

    }

}
