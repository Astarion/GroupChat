import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Server {
    private static int sessionId = 0;
    private static volatile int sessionCounter = 0; //volatile не помогает для синхронизации доступа к переменной, т.к. мы изменяем её не атомарной операцией
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
                    Thread thread = new Thread(new Session(socket, serverSocket));
                    System.out.println("Number of session: " + sessionCounter);
                    thread.start();
                }


            }

        }catch (SocketException e)
        {
            System.out.println("Some problems: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid format of an argument: " + e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Wrong argument: " + e.getMessage());
        }





    }
    public static void threadStop(){
        sessionCounter--;
        System.out.println("Number of session: " + sessionCounter);
    }


}
// Сделать счетчик, который контролирует количество одновременных подключений
// Если пытаается подключиться, когда нет места, ему выводится сообщение "Подожди"
//Сделать красивую обработку исключений