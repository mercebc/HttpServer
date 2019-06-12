package Core;

import Request.Request;
import Request.RequestConverter;
import Response.Response;
import Response.ResponseConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Server {
    private Listener listener;
    private Router router;
    private ServerStatus serverStatus;

    public Server(Listener listener, Router router, ServerStatus serverStatus){
        this.listener = listener;
        this.router = router;
        this.serverStatus = serverStatus;
    }

    public void start() throws IOException, InvocationTargetException, IllegalAccessException {
        while (serverStatus.isOn()) {
            SocketIO socketIO = listener.connect();
            Response response = router.route(getRequest(socketIO));
            sendResponse(socketIO, response);
        }
    }

    private void sendResponse(SocketIO socketIO, Response response) throws IOException {
        String responseString = ResponseConverter.responseToString(response);
        socketIO.printToSocket(responseString);
    }

    private Request getRequest(SocketIO socketIO) throws IOException {
        String requestString = socketIO.readFromSocket();
        return RequestConverter.stringToRequest(requestString);
    }

}
