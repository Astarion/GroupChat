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
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new Session(socket));
                thread.start();
            }
//            InputStream inputStream = socket.getInputStream();
//            DataInputStream dataInputStream = new DataInputStream(inputStream);
//            String message;
//            while (true) {
//                message = dataInputStream.readUTF();
//                System.out.println(message);
////                if (message.equals("quit")) {
////                    System.out.println("Server is shutting down");
////                    break;
////                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
// Сделать счетчик, который контролирует количество одновременных подключений
// Если пытаается подключиться, когда нет места, ему выводится сообщение "Подожди"