package Handlers;

import Request.Request;
import Response.Response;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ResponseHandler {
    private HashMap<String, String> allowedMethods = new HashMap<>();
    private Set<String> httpMethods = new HashSet<>(Arrays.asList("GET", "POST", "HEAD", "OPTIONS", "PUT"));

    public HashMap<String, String> allowedMethods() {
            return allowedMethods;
    }

    public Response respondToRequest(Request request) {
        addMethodsAllowedIntoHeader(this.getClass());

        for (Method method : handlerHttpMethods(this.getClass())) {
            if(isMethodAllowed(request, method)){
                return new Invoke(this, method).toRespondTo(request);
            }
        }
        return Response.methodNotAllowed().withHeaders(allowedMethods);
    }

    private boolean isMethodAllowed(Request request, Method method) {
        return request.getMethod().equals(method.getName().toUpperCase());
    }

    private void addMethodsAllowedIntoHeader(Class<?> aClass){
        StringBuilder methodsAllowed = new StringBuilder();
        for (Method method : handlerHttpMethods(aClass)) {
            methodsAllowed.append(method.getName().toUpperCase()).append(",");
        }
        methodsAllowed = new StringBuilder(methodsAllowed.substring(0, methodsAllowed.length() - 1));
        this.allowedMethods.put("Allow", methodsAllowed.toString());
    }

    private List<Method> handlerHttpMethods(Class<?> currentClass){
        List<Method> handlerMethods = Arrays.asList(currentClass.getDeclaredMethods());

        List<Method> handlerHttpMethods =
                handlerMethods.stream()
                        .filter(method -> httpMethods.contains(method.getName().toUpperCase()))
                        .collect(Collectors.toList());

        return handlerHttpMethods;
    }

}
