package Core;

import Handlers.*;
import Request.Request;
import Response.Response;
import Util.PublicFileManager;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private PublicFileManager publicFileManager;

    public Router(PublicFileManager publicFileManager){
        this.publicFileManager = publicFileManager;
    }

    private Map<String, ResponseHandler> resources = new HashMap<>();

    private void addResource(String resource, ResponseHandler responseHandler){
        resources.put(resource, responseHandler);
    }

    public Map<String, ResponseHandler> getResources() {
        return resources;
    }

    public Response route(Request request) {
        String URI = request.getURI();
        ResponseHandler resourceHandler = resources.get(URI);
        ResponseHandler fileHandler = publicFileManager.getFiles().get(URI);

        if (resourceHandler != null) {
            return resourceHandler.respondToRequest(request);
        }else if (fileHandler != null) {
            return fileHandler.respondToRequest(request);
        }else{
            return Response.notFound();
        }
    }

    public void config(){
        addResource("/", new IndexHandler(publicFileManager));
        addResource("/simple_get", new SimpleGetHandler());
        addResource("/redirect", new RedirectHandler());
        addResource("/get_with_body", new GetWithBodyHandler());
        addResource("/method_options", new MethodOptionsHandler());
        addResource("/method_options2", new MethodOptions2Handler());
        addResource("/echo_body", new EchoBodyHandler());
    }
}


