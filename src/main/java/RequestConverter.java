public class RequestConverter {
    private String space;

    public RequestConverter() {
        this.space = "\\s+";
    }

    //Request

    //string to RequestLine

    //create Request with RequestLine

    //string to Headers

    //add headers, if any, to Request

    //string to message_body

    //add message_body, if any, to Request


    public Request stringToRequest(String requestLine){
        return createRequest(requestLine.split(space));
    }


    public Request createRequest(String[] rq){
        return new Request(rq[0], rq[1], rq[2]);
    }
}
