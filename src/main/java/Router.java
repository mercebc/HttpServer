import java.util.HashMap;
import java.util.Map;

public class Router {

    private Map<Route, Handler> routes = new HashMap<>();

    public void addRoute(Route route, Handler handler){
        routes.put(route, handler);
    }

    public Response route(Request request){

        Route route = new Route(request.getVerb(), request.getURI());

        addRoute(new Route("GET", "/simple_get"), new OKResponseHandler());

        System.out.println(route.getMethod());
        if (routes.get(route) != null) {
            return routes.get(route).respondToRequest();
        }

        return null;

    }

}


