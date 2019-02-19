public class ResponseConverter {



    public String responseToString(Response response) {

       String statusLine = response.getHeaders();
       String messageBody = "\n" + response.getBody();

       return statusLine + "\n" + messageBody;

    }

}
