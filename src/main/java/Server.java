import Handlers.Response;

import java.io.IOException;

public class Server {
    private Listener listener;
    private Router router;

    public Server(Listener listener, Router router){
        this.listener = listener;
        this.router = router;
    }

    public void start() throws IOException {
        while (true) {
            Communicator communicator = new Communicator(listener.connect());
            Response response = router.route(communicator.getRequest());
            communicator.sendResponse(response);
        }
    }

}
