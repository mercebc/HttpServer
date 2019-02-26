import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {

        int portNumber = 5000;
        Listener listener = new Listener(new ServerSocket(portNumber));

        Router router = new Router();
        router.config();

        Server server = new Server(listener, router);
        server.start();

    }
}
