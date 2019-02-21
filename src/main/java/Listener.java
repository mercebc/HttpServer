import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {

    private ServerSocket serverSocket;

    public ServerListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public SocketHelper connect() throws IOException {
        return new SocketHelper(serverSocket.accept());
    }

}
