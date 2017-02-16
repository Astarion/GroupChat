import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            while (true) {
                str = bufferedReader.readLine();
                dataOutputStream.writeUTF(str);

                if (str.equals("quit")) {
                    System.out.println("Client's connection was closed");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
