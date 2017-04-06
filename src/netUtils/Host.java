package netUtils;

import concurrentUtils.Channel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Host implements Runnable{

    private Integer port;
    private Channel channel;
    private ServerSocket serverSocket;
    private MessageHandlerFactory messageHandlerFactory;

    public Host(Integer port, Channel channel, MessageHandlerFactory messageHandlerFactory) {
        this.port = port;
        this.channel = channel;
        this.messageHandlerFactory = messageHandlerFactory;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.out.printf("Port problems\n%s%n", e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                channel.put(new Session(socket, messageHandlerFactory.createMessageHandler()));
            }
        } catch (SocketException e) {
            System.out.println("Some problems: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.printf("Port problems\n%s%n", e.getMessage());
        }
    }
}
