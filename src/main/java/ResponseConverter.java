public class ResponseConverter {



    public String responseToString(Response response) {

       String statusLine = response.getProtocol() + " " + response.getCode() + " " + response.getStatus();
       String messageBody = "\n" + response.getBody();

       return statusLine + "\n" + messageBody;

    }

}
