public class Request {
    private String verb;
    private String URI;
    private String protocol;

    public Request(String verb, String URI, String protocol){
        this.verb = verb;
        this.URI = URI;
        this.protocol = protocol;
    }

    public String getVerb() {
        return verb;
    }
}
