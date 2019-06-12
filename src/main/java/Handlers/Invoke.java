package Handlers;

import Request.Request;
import Response.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoke {
    private final Object object;
    private final Method method;

    public Invoke(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Response toRespondTo(Request request) {
        try {
            return (Response) method.invoke(object, request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return Response.status(500);
        }
    }
}
