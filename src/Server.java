import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1500);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message = dataInputStream.readUTF();
            System.out.println(message);
//            System.out.println("Server is shutting down");
        }
        catch (Exception e)
        {

        }


    }
}
