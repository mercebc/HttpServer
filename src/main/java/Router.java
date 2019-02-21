import Handlers.*;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private Map<Resource, ResponseHandler> routes = new HashMap<>();

    public void addResource(Resource resource, ResponseHandler responseHandler){
        routes.put(resource, responseHandler);
    }

    public Map<Resource, ResponseHandler> getRoutes() {
        return routes;
    }

    public Response route(Request request){

        Resource resource = new Resource(request.getMethod(), request.getURI());

        if (routes.get(resource) != null) {
            return routes.get(resource).respondToRequest();
        }else{
            return new NotFoundHandler().respondToRequest();
        }

    }

    public void config(){
        addResource(new Resource("GET", "/simple_get"), new SimpleGetHandler());
        addResource(new Resource("HEAD", "/simple_get"), new SimpleGetHandler());
        addResource(new Resource("HEAD", "/get_with_body"), new SimpleGetHandler());
        addResource(new Resource("GET", "/redirect"), new RedirectHandler());
    }
}


