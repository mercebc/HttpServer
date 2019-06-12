package Handlers;

import Request.Request;
import Response.Response;
import Response.StatusLineBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ResponseHandler {
    private HashMap<String, String> headers = new HashMap<>();

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String name, String value){
        this.headers.put(name, value);
    }

    public Response respondToRequest(Request request) throws InvocationTargetException, IllegalAccessException {
        List<Method> methods = filterHTTPMethods(this.getClass());

        for (Method method : methods) {
            if(request.getMethod().equals(method.getName().toUpperCase())){
                addAllowedMethodsHeader(this.getClass());
                return (Response) method.invoke(this, request);
            }
        }
        return new Response(StatusLineBuilder.create(405), getHeaders());
    }

    public void addAllowedMethodsHeader(Class<?> currentClass){
        List<Method> classHttpMethods = filterHTTPMethods(currentClass);
        String methodsAllowed = "";

        for (Method method : classHttpMethods)
        {
            methodsAllowed += method.getName().toUpperCase() + ",";
        }
        methodsAllowed = methodsAllowed.substring(0, methodsAllowed.length() - 1); //to not get the last comma and space
        addHeader("Allow", methodsAllowed);
    }

    private List<Method> filterHTTPMethods(Class<?> currentClass){
        Set<String> httpMethods = new HashSet<>(Arrays.asList("GET", "POST", "HEAD", "OPTIONS", "PUT"));
        List<Method> classMethods = Arrays.asList(currentClass.getDeclaredMethods());

        List<Method> classHttpMethods =
                classMethods.stream()
                        .filter(method -> httpMethods.contains(method.getName().toUpperCase()))
                        .collect(Collectors.toList());

        return classHttpMethods;
    }
}
