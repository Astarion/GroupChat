import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Alex on 17.02.2017.
 */
public class Session implements Runnable {
    Socket socket;
    public Session(Socket socket)
    {
        this.socket = socket;
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

                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
