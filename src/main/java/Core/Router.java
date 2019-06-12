package Core;

import Handlers.*;
import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;
import Util.PublicFileManager;

import java.lang.reflect.InvocationTargetException;
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

    public Response route(Request request) throws InvocationTargetException, IllegalAccessException {

        String URI = request.getURI();

        if (resources.get(URI) != null) {
            return resources.get(URI).respondToRequest(request);
        }else if (publicFileManager.getFiles().get(URI) != null) {
            return publicFileManager.getFiles().get(URI).respondToRequest(request);
        }else{
            return new Response(StatusLineBuilder.create(404));
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


