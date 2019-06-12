package Core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

    private ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public SocketIO connect() throws IOException {
        Socket socket = serverSocket.accept();
        return new SocketIO(socket);
    }

}
