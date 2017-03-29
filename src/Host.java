import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Host {

    private Integer maxSessionCount;
    private Integer port;
    private Channel channel;

    public Host(Integer port, Channel channel) {
        this.port = port;
        this.maxSessionCount = 5;
        this.channel = channel;
    }

    public Host(Integer port, Integer maxSessionCount) {
        this.port = port;
        this.maxSessionCount = maxSessionCount;
        channel = new Channel(this.maxSessionCount);
    }

    public Host(Integer port) {
        this.port = port;
        this.maxSessionCount = 5;
        channel = new Channel(this.maxSessionCount);
    }

    public Host() {
        this.port = 5000;
        this.maxSessionCount = 5;
        channel = new Channel(this.maxSessionCount);
    }

    public void start() {
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                channel.put(new Session(socket));
            }
        } catch (SocketException e) {
            System.out.println("Some problems: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.printf("Port problems\n%s%n", e.getMessage());
        }
    }
}
