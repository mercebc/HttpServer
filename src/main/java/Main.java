import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {

        int portNumber = 5000;

        Listener listener = new Listener(new ServerSocket(portNumber));

    }
}
