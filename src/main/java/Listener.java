import java.io.IOException;
import java.net.ServerSocket;

public class Listener {

    private ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public SocketHelper connect() throws IOException {
        return new SocketHelper(serverSocket.accept());
    }

}
