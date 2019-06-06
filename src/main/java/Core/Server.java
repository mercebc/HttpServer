package Core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Server {
    private Listener listener;
    private Router router;

    public Server(Listener listener, Router router){
        this.listener = listener;
        this.router = router;
    }

    public void start() throws IOException, InvocationTargetException, IllegalAccessException {
        while (true) {
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
