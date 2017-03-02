import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Server {
    private static int sessionId = 0;
    private static int sessionCounter = 0;
    private static int maxSessionCount = 5;
    public static void main(String[] args) throws IOException {
        try {

            Integer port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {

                if (sessionCounter < maxSessionCount) {
                    sessionId++;
                    sessionCounter++;
                    Socket socket = serverSocket.accept();
                    Thread thread = new Thread(new Session(socket));
                    System.out.println("Number of session: " + sessionCounter);
                    thread.start();
                }


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
    public void threadStop(){
        sessionCounter--;
        System.out.println("Number of session: " + sessionCounter);
    }


}
// Сделать счетчик, который контролирует количество одновременных подключений
// Если пытаается подключиться, когда нет места, ему выводится сообщение "Подожди"