import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Server {
    //    private static int sessionId = 0;
    private static int sessionCounter = 0; //volatile не помогает для синхронизации доступа к переменной, т.к. мы изменяем её не атомарной операцией
    private static int maxSessionCount = 5;
    private final static Object lock = new Object();

    public static void main(String[] args) throws IOException {
        try {

            Integer port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                synchronized (lock) {
                    if (sessionCounter >= maxSessionCount)
                        lock.wait();
                    sessionCounter++;
                    System.out.println("Number of sessions: " + sessionCounter);
                }

                Thread thread = new Thread(new Session(socket));
                thread.start();
            }
        } catch (SocketException e) {
            System.out.println("Some problems: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of an argument: " + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong argument: " + e.getMessage());
            return;
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception: " + e.getMessage());
        }
    }

    public static void threadStop() {
        synchronized (lock) {
            sessionCounter--;
            System.out.println("Number of sessions: " + sessionCounter);
            lock.notify();
        }
    }


}
// Сделать счетчик, который контролирует количество одновременных подключений
// Если пытаается подключиться, когда нет места, ему выводится сообщение "Подожди"
//Сделать красивую обработку исключений