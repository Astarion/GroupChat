import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

/**
 * Created by Alex on 17.03.2017.
 */
public class Dispatcher implements Runnable {

    private final Channel channel;
    public Dispatcher(Channel channel) {
        this.channel = channel;
    }
    @Override
    public void run() {
        while(true){
            Runnable session = channel.take();
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
