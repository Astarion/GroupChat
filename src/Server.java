import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        try {

            Integer port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message;
            while (true) {
                message = dataInputStream.readUTF();
                System.out.println(message);
//                if (message.equals("quit")) {
//                    System.out.println("Server is shutting down");
//                    break;
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
