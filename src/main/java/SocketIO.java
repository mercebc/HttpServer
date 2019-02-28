import java.io.*;
import java.net.Socket;

public class SocketIO {
    private Socket socket;
    private boolean messageSent = false;

    public SocketIO(Socket socket) {
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

        System.out.println(request.toString());
        return request.toString();
    }


    public void printToSocket(String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.print(message);
        messageSent = !out.checkError();
        closeConnection();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }

}
