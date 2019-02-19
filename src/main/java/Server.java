import java.io.IOException;

public class Server {
    private ServerListener serverListener;

    public Server(ServerListener serverListener){
        this.serverListener = serverListener;
    }

    public void start() throws IOException {
        while (true) {
            SocketCommunicator socketCommunicator = serverListener.connect();
            new ServerCommunicator(socketCommunicator).handleRequest();
        }
    }
}
