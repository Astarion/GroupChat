package app;

import netUtils.Host;
import netUtils.MessageHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Alex on 12.05.2017.
 */
public class SendClientsMessageHandler implements MessageHandler {

    private Host host;

    SendClientsMessageHandler(Host host) {
        this.host = host;
    }

    @Override
    public String handle(String message) {
        try {
            ArrayList<Socket> clients = host.getAllClients();
            DataOutputStream dataOutputStream;
            for (int i = 0; i < clients.size(); i++) {
                Socket socket = clients.get(i);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
