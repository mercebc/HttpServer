package Core;

import Handlers.*;
import Handlers.PublicFileHandlers.CssHandler;
import Handlers.PublicFileHandlers.ImageHandler;
import Util.PublicFileReader;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Router {

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
        }else{
            return new Response(StatusLineBuilder.create(404));
        }

    }

    public void publicFiles(PublicFileReader publicFileReader){
        addResource("/images/wellies.png", new ImageHandler(publicFileReader));
        addResource("/css/app-stylesheet.css", new CssHandler(publicFileReader));
    }

    public void config(PublicFileReader publicFileReader){
        addResource("/", new IndexHandler(publicFileReader));
        addResource("/simple_get", new SimpleGetHandler());
        addResource("/redirect", new RedirectHandler());
        addResource("/get_with_body", new GetWithBodyHandler());
        addResource("/method_options", new MethodOptionsHandler());
        addResource("/method_options2", new MethodOptions2Handler());
        addResource("/echo_body", new EchoBodyHandler());
    }
}


