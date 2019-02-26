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
                addAllowedMethods(this.getClass());
                return (Response) method.invoke(this, request);
            }
        }
        return new Response("HTTP/1.1 404 Not Found");
    }

    public Response get(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed", getHeaders());
    }

    public Response post(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed", getHeaders());
    }

    public Response head(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed", getHeaders());
    }

    public Response options(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed", getHeaders());
    }

    public Response put(Request request) {
        return new Response("HTTP/1.1 405 Method Not Allowed", getHeaders());
    }


    public void addAllowedMethods(Class<?> currentClass){
        Method[] methods = currentClass.getDeclaredMethods();
        String methodsAllowed = "";
        for (Method method : methods)
        {
            methodsAllowed += method.getName().toUpperCase() + ",";
        }
        methodsAllowed = methodsAllowed.substring(0, methodsAllowed.length() - 1); //to not get the last comma and space

        addHeader("Allow", methodsAllowed);
    }
}
