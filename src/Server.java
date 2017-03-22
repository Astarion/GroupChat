import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Server {

    private Integer sessionCounter = 0; //volatile не помогает для синхронизации доступа к переменной, т.к. мы изменяем её не атомарной операцией
    private Integer maxSessionCount;
    private Integer port;
    private final Object lock = new Object();
    private Channel channel;
    private Dispatcher dispatcher;

    public Server(Integer port, Integer maxSessionCount)
    {
        this.port = port;
        this.maxSessionCount = maxSessionCount;
        channel = new Channel(this.maxSessionCount);
        dispatcher = new Dispatcher(channel);
    }

    public Server(Integer port)
    {
        this.port = port;
        this.maxSessionCount = 5;
        channel = new Channel(this.maxSessionCount);
        dispatcher = new Dispatcher(channel);
    }

    public Server()
    {
        this.port = 5000;
        this.maxSessionCount = 5;
        channel = new Channel(this.maxSessionCount);
        dispatcher = new Dispatcher(channel);
    }


    public void start() {
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            Thread thread = new Thread(dispatcher);
            thread.start();
            while (true) {
                Socket socket = serverSocket.accept();
                synchronized (lock) {
                    while (sessionCounter >= maxSessionCount)
                        lock.wait();
                    sessionCounter++;
                    System.out.println("Number of sessions: " + sessionCounter);
                }
                channel.put(new Session(socket, this));
            }

        } catch (SocketException e) {
            System.out.println("Some problems: " + e.getMessage());
            return;
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.printf("Port problems\n%s%n", e.getMessage());
        }
    }

    public void threadStop() {
        synchronized (lock) {
            sessionCounter--;
            System.out.println("Number of sessions: " + sessionCounter);
            lock.notifyAll();
        }
    }
}
