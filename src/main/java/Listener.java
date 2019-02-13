import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

    private ServerSocket ss;

    public Listener(ServerSocket ss) {
        this.ss = ss;
    }

    public StreamSocket connect() throws IOException {
        return convertToStreamSocket(ss.accept());
    }

    public StreamSocket convertToStreamSocket(Socket socket) throws IOException {
        return new StreamSocket(socket.getInputStream(), socket.getOutputStream());
    }

}
