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


        System.out.println(new MerceController().get());
        System.out.println(new MerceController().post());

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
        // addResource("/resource", new MerceController());
    }
}

abstract class Controller {

    Response get() {
        return new Response("405");
    }

    Response post() {
        return new Response("405");
    }

}

class MerceController extends Controller {

    public Response get() {
        return new Response("200");
    }


}

