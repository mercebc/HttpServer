public class Translator {
   private String space;

   public Translator(){
       this.space = "\\s+";
   }

    public Request StringToRequest(String request){
        return createRequest(request.split(space));
    }

    public Request createRequest(String[] rq){
        return new Request(rq[0], rq[1], rq[2]);
    }
}
