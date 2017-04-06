package concurrentUtils;

import concurrentUtils.Channel;

/**
 * Created by Alex on 17.03.2017.
 */
public class Dispatcher implements Runnable {

    private final Channel channel;
    private ThreadPool threadPool;
    public Dispatcher(Channel channel, ThreadPool threadPool) {

        this.channel = channel;
        this.threadPool = threadPool;
    }
    @Override
    public void run() {
        while(true){
            Runnable session = channel.take();
            threadPool.execute(session);
        }
    }
}
