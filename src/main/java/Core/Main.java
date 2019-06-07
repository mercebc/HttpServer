package Core;

import Util.PublicFileReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {

        int portNumber = 5000;
        String path = "/Users/miss.merce/Workspace/Java/HttpServer/Public/";

        Listener listener = new Listener(new ServerSocket(portNumber));

        Router router = new Router();
        PublicFileReader publicFileReader = new PublicFileReader(path);
        router.config(publicFileReader);

        Server server = new Server(listener, router);
        server.start();

    }
}
