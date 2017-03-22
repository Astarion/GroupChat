import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Alex on 17.02.2017.
 */
public class Session implements Runnable {
    Socket socket;
    Server server;
    public Session(Socket socket, Server server)
    {
        this.socket = socket;
        this.server = server;
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message;
            while (true) {
                message = dataInputStream.readUTF();
                if(message.equals("quit"))
                {
                    return;
                }

                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Connection interrupted");
            return;
        }
        finally {
            server.threadStop();
        }

    }
}
