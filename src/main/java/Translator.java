public class Translator {
   private String space;

   public Translator(){
       this.space = "\\s+";
   }

    public Request stringToRequest(String request){
        return createRequest(request.split(space));
    }

    public Request createRequest(String[] rq){
        return new Request(rq[0], rq[1], rq[2]);
    }

    public String responseToString(Response response) {

       String statusLine = response.getProtocol() + " " + response.getCode() + " " + response.getStatus();
       String messageBody = response.getBody();

       return statusLine + "\n\n" + messageBody;

    }
}
