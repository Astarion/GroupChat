package app;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.IllegalFormatException;

/**
 * Created by 14Malgavka on 10.02.2017.
 */
public class Client {

    public static void main(String[] args) throws IOException {

        try {
            String host = args[0];
            Integer port = Integer.parseInt(args[1]);
            Socket socket = new Socket(host, port);
//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String str;
//            String messageFromHost;
            while (true) {
                str = bufferedReader.readLine();
                dataOutputStream.writeUTF(str);
//                messageFromHost = dataInputStream.readUTF();
//                if (messageFromHost.equals("Server stopped")) {
//                    System.out.println(messageFromHost);
//                    break;
//                }

                if (str.equals("quit")) {
                    System.out.println("app.Client's connection was closed");
                    socket.close();
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong params in app.Client");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("Problem with host occured: " + e.getMessage() + "\nEnter correct host address");
        } catch (ConnectException e) {
            System.out.println("Connection problem occured:\n" + e.getMessage());
        } catch (SocketException e) {
            System.out.println("Connection was closed by host:\n" + e.getMessage());
        }


    }
}
//ДЗ: изучить интерфейсы BlockingQueue
//Доделать методы Stop(), применяя флаг isActive и в диспетчере и в хосте
//Должен ли быть isAlive volatile или нет