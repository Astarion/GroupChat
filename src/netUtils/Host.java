package netUtils;

import concurrentUtils.Channel;
import concurrentUtils.Stoppable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Host implements Stoppable {

    private Integer port;
    private Channel channel;
    private ServerSocket serverSocket;
    private MessageHandlerFactory messageHandlerFactory;
    private volatile boolean isActive;
    private ArrayList<Socket> allClients = new ArrayList<Socket>();

    public Host(Integer port, Channel channel, MessageHandlerFactory messageHandlerFactory) {
        this.port = port;
        this.channel = channel;
        this.messageHandlerFactory = messageHandlerFactory;
        isActive = true;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.out.printf("Port problems\n%s%n", e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (isActive) {
                Socket socket = serverSocket.accept();
                channel.put(new Session(socket, messageHandlerFactory.createMessageHandler(this)));
                allClients.add(socket);
            }
        } catch (SocketException e) {
            System.out.println("Some problems: " + e.getMessage());
        } catch (IOException e) {
            System.out.printf("Port problems\n%s%n", e.getMessage());
        }
    }

    @Override
    public void stop() {
        if (isActive)
            isActive = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Socket> getAllClients()
    {
        return allClients;
    }
}
