import java.io.IOException;

public class Server {
    private Listener listener;

    public Server(Listener listener){
        this.listener = listener;
    }

    public void start() throws IOException {
        while (true) {
            StreamSocket sc = listener.connect();
            new Communicator(sc).run();
        }
    }
}
