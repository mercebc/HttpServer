package Handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class ResponseHandler {
    private HashMap<String, String> headers = new HashMap<>();

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String name, String value){
        this.headers.put(name, value);
    }

    public Response respondToRequest(Request request) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = ResponseHandler.class.getMethods();

        for (Method method : methods) {
            if(request.getMethod().equals(method.getName().toUpperCase())){
                Response response = (Response) method.invoke(this, request);
                System.out.println(response);
                return response;
            }
        }
        return null;
    }

    public Response get(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed");
    }

    public Response post(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed");
    }

    public Response head(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed");
    }

    public Response options(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed");
    }

    public Response put(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed");
    }
}
