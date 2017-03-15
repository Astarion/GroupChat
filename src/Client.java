import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
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
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String str;
//            String messageFromServer;
//            messageFromServer = dataInputStream.readUTF();
//            System.out.println(messageFromServer);

            while (true) {

                str = bufferedReader.readLine();
                dataOutputStream.writeUTF(str);
                if (str.equals("quit")) {
                    System.out.println("Client's connection was closed");
                    socket.close();
                    break;
                }

            }
        } catch (NumberFormatException e) {

            System.out.println("Wrong params in Client");
            return;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Illegal argument: " + e.getMessage());
            return;
        }
        catch (UnknownHostException e)
        {
            System.out.println("Problem with host occured: " + e.getMessage() + "\nEnter correct host address");
            return;
        }
        catch (ConnectException e)
        {
            System.out.println("Connection problem occured:\n" + e.getMessage());
            return;
        }
        catch (SocketException e)
        {
            System.out.println("Connection was closed by server:\n" + e.getMessage());
            return;
        }


    }
}
