public class Generator {


    public Response generateResponse(Request request){

        Response response = new Response();

        if(request.getVerb().equals("GET") && request.getURI().equals("/simple_get")){
            response.setCode(200);
            response.setStatus("OK");
            response.setBody("");
        }

        if(request.getVerb().equals("HEAD") && request.getURI().equals("/simple_get")){
            response.setCode(200);
            response.setStatus("OK");
            response.setBody("");
        }

        if(request.getVerb().equals("GET") && request.getURI().equals("/not_found_resource")){
            response.setCode(404);
            response.setStatus("Not found");
            response.setBody("");
        }

        return response;

    }

}
