package Core;

import java.io.IOException;
import java.net.ServerSocket;

public class Listener {

    private ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public SocketIO connect() throws IOException {
        return new SocketIO(serverSocket.accept());
    }

}
