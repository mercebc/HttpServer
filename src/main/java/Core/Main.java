package Core;

import Util.PublicFileManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {

        int portNumber = Integer.parseInt(System.getenv("PORT"));
        String path = System.getProperty("path") + "/";

        Listener listener = new Listener(new ServerSocket(portNumber));

        PublicFileManager publicFileManager = new PublicFileManager(path);
        Router router = new Router(publicFileManager);

        router.config();
        publicFileManager.config();

        ServerStatus status = new ServerStatus(true);

        Server server = new Server(listener, router, status);
        server.start();

    }
}
