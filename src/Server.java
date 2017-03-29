/**
 * Created by Alex on 22.03.2017.
 */
public class Server {
    public static void main(String[] args) {
        try {
            Integer port = Integer.parseInt(args[0]);
            Integer maxSession = Integer.parseInt(args[1]);
            Channel channel = new Channel(maxSession);
            ThreadPool threadPool = new ThreadPool(maxSession);
            Dispatcher dispatcher = new Dispatcher(channel, threadPool);
            Thread dispatcherThread = new Thread(dispatcher);
            dispatcherThread.start();
            Host host = new Host(port, channel);
            host.start();
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of an argument: " + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong argument: " + e.getMessage());
            return;
        }
    }
}
