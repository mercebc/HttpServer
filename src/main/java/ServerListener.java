import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {

    private ServerSocket serverSocket;

    public ServerListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public SocketCommunicator connect() throws IOException {
        return new SocketCommunicator(serverSocket.accept());
    }

}
