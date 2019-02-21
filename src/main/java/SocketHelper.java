import java.io.*;
import java.net.Socket;

public class SocketHelper {
    private Socket socket;
    private boolean messageSent = false;

    public SocketHelper(Socket socket) {
        this.socket = socket;
    }

    public boolean isMessageSent() {
        return messageSent;
    }

    public Socket getSocket() {
        return socket;
    }

    public String readFromSocket() throws IOException {
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream request = new ByteArrayOutputStream();

        do {
            int result = inputStream.read();
            request.write((byte) result);
        } while (inputStream.available() > 0);

        return request.toString();
    }


    public void printToSocket(String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        messageSent = !out.checkError();
        closeConnection();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }

}
