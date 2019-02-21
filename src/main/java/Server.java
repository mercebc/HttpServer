import java.io.IOException;

public class Server {
    private ServerListener serverListener;
    private Router router;

    public Server(ServerListener serverListener){
        this.serverListener = serverListener;
        this.router = new Router();
    }

    public void start() throws IOException {
        while (true) {
            ServerCommunicator serverCommunicator = new ServerCommunicator(serverListener.connect());
            Response response = router.route(serverCommunicator.getRequest());
            serverCommunicator.sendResponse(response);
        }
    }
}
