import java.io.IOException;

/**
 * Created by Alex on 22.03.2017.
 */
public class Host {
    public static void main(String[] args) {
        try {
            Integer port = Integer.parseInt(args[0]);
            Server server = new Server(port);
            server.start();
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of an argument: " + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong argument: " + e.getMessage());
            return;
        }
    }
}
